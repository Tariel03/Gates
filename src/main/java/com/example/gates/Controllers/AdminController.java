package com.example.gates.Controllers;

import com.example.gates.Dto.*;
import com.example.gates.Dto.Update.*;
import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.*;
import com.example.gates.Photos.PhotoConfig;
import com.example.gates.Services.*;
import com.example.gates.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    JwtUtil jwtUtil;
    GatesService gatesService;
    AuthenticationManager authenticationManager;
    DoneService doneService;
    ModelMapper modelMapper;
    NewsService newsService;
    OrderService orderService;
    ChangeService changeService;
    ServicesService servicesService;
    AdditionalService additionalService;
    RegistrationService registrationService;
    //No need for Autowiring!
    PhotoConfig photoConfig = new PhotoConfig();
    @Autowired
    public AdminController(JwtUtil jwtUtil, GatesService gatesService, AuthenticationManager authenticationManager, DoneService doneService, ModelMapper modelMapper, NewsService newsService, OrderService orderService, ChangeService changeService, ServicesService servicesService, AdditionalService additionalService, RegistrationService registrationService) {
        this.jwtUtil = jwtUtil;
        this.gatesService = gatesService;
        this.authenticationManager = authenticationManager;
        this.doneService = doneService;
        this.modelMapper = modelMapper;
        this.newsService = newsService;
        this.orderService = orderService;
        this.changeService = changeService;
        this.servicesService = servicesService;
        this.additionalService = additionalService;
        this.registrationService = registrationService;
    }

    @PostMapping("/gates/save")
    @Operation(summary = "Save gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<Gates> saveGates(@RequestParam String description, @RequestParam("file") MultipartFile multipartFile,@RequestParam int type_id ) {
        Gates gates = new Gates();
        gates.setGatesType(gatesService.findTypeById(type_id));
        gates.setDescription(description);
        photoConfig.savePhoto(multipartFile);
        gates.setLink(photoConfig.getPath()+"/"+multipartFile.getOriginalFilename());
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }
    @DeleteMapping("/gates/delete/{id}")
    @Operation(summary = "Delete gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<HttpStatus> deleteGates(@PathVariable("id") int id) {
        gatesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Done save", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Done.class))))})
    @PostMapping("/done/save")
    public ResponseEntity<Done> saveDone(@RequestParam int order_id, @RequestParam("file") MultipartFile file){
        Done done = new Done();
        Order order = orderService.findById(order_id).get();
        order.setStatus("done");
        done.setOrder(order);
        photoConfig.savePhoto(file);
        done.setLink(photoConfig.getPath()+"/"+file.getOriginalFilename());
        doneService.save(done);
        return ResponseEntity.ok(done);
    }
    @Operation(summary = "Delete done", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Done.class))))})
    @DeleteMapping("/done/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDone( @PathVariable  int id){
        doneService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/news/save")
    @Operation(summary = "Write news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class)))) })
    public ResponseEntity<News> saveNews(@RequestParam String description, @RequestParam String header, @RequestParam("file1") MultipartFile file, @RequestParam("file2") MultipartFile multipartFile){

        News news = new News();
        news.setDate(LocalDate.now());
        news.setHeader(header);
        news.setDescription(description);
        photoConfig.savePhoto(file);
        photoConfig.savePhoto(multipartFile);
        news.setMain_photo(photoConfig.getPath()+"/"+file.getOriginalFilename());
        news.setSecond_photo(photoConfig.getPath()+"/"+multipartFile.getOriginalFilename());
        news.setAdmin(registrationService.currentUser());

        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Delete comment", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @DeleteMapping("/news/delete")
    public ResponseEntity<HttpStatus> deleteNews(int id){
        newsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Operation(summary = "Write review", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDto.class))))})
    @PostMapping("/review/save")
    public ResponseEntity<Review> saveReview(@Valid @RequestBody ReviewDto reviewDto,@RequestParam int id, BindingResult result){
        if(result.hasErrors()){
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error:fieldErrors
                 ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Review review   = convertToReview(reviewDto);
        review.setGatesType(gatesService.findTypeById(id));
        additionalService.saveReview(review);
        return ResponseEntity.ok(review);
    }
    @Operation(summary = "Save services", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PostMapping("services/save")
    public ResponseEntity<Services> saveServices(@RequestParam String name , @RequestParam MultipartFile file){
        Services services = new Services();
        photoConfig.savePhoto(file);
        services.setName(name);
        services.setLink(photoConfig.getPath()+"/"+file.getOriginalFilename());
        servicesService.save(services);
        return ResponseEntity.ok(services);
    }
    @PostMapping("/order/save")
    @Operation(summary = "Make order", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrdersDto.class)))) })
    public ResponseEntity<String> save(@RequestBody OrdersDto ordersDto, BindingResult result){
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Order  order = convertToOrder(ordersDto);
        orderService.receivedStatus(order);
        Changes changes = changeService.generate(order,order.getStatus(), registrationService.currentUser());
        changeService.save(changes);
        orderService.save(order);
        return ResponseEntity.ok("In process");
    }

    @GetMapping("/changes")
    public List<Changes> allChanges(){
        return changeService.allChanges();
    }


    @GetMapping("/changes/{id}")
    public ResponseEntity<Changes> findById(@PathVariable int id ){
        return ResponseEntity.ok(changeService.findById(id));
    }
    @Operation(summary = "Update an order", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderUpdateDto.class))))})
    @PutMapping("/order/change/{id}")
    public ResponseEntity<String> update(@PathVariable int id){
        Order order = orderService.findById(id).get();
        order.setAdmin(registrationService.currentUser());
        orderService.updatedStatus(order, "updated");
        orderService.save(order);
        Changes changes = changeService.generate(order, order.getStatus(), registrationService.currentUser());
        changeService.save(changes);
        return ResponseEntity.ok("Order status has been changed");
    }
    @Operation(summary = "Save new gates_type", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PostMapping("gates_types/save")
    public ResponseEntity<String> saveType(@RequestParam String type, @RequestParam("file")MultipartFile file){

        gatesType gatesType = new gatesType();
        gatesType.setType(type);
        photoConfig.savePhoto(file);
        gatesType.setLink(photoConfig.getPath()+"/"+file.getOriginalFilename());
        gatesService.save(gatesType);
        return ResponseEntity.ok("Successfully created!");
    }
    @Operation(summary = "Update gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PutMapping("/gates/change/description/{id}")
    public ResponseEntity<Gates>changeDesc(@PathVariable int id, @RequestParam String description){
        Gates gates = gatesService.findById(id).get();
        gates.setDescription(description);
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }

    @Operation(summary = "Update gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PutMapping("/gates/change/photo/{id}")
    public ResponseEntity<Gates>changePhoto(@PathVariable int id, @RequestParam("file") MultipartFile multipartFile){
        Gates gates = gatesService.findById(id).get();
        photoConfig.savePhoto(multipartFile);
        gates.setLink(photoConfig.getPath()+"/"+multipartFile.getOriginalFilename());
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }
    @Operation(summary = "Get all changes", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = gatesType.class))))})
    @PutMapping("/gates_type/change/photo/{id}")
    public ResponseEntity<gatesType>changeType(@PathVariable int id, @RequestParam("file")MultipartFile file){
        gatesType gatesType = gatesService.findTypeById(id);
        photoConfig.savePhoto(file);
        gatesType.setLink(photoConfig.getPath()+"/"+file.getOriginalFilename());
        gatesService.save(gatesType);
        return ResponseEntity.ok(gatesType);
    }
    @Operation(summary = "Get all changes", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = gatesType.class))))})
    @PutMapping("/gates_type/change/type/{id}")
    public ResponseEntity<gatesType>changeTypePhoto(@PathVariable int id, @RequestParam("type")String type){
        gatesType gatesType = gatesService.findTypeById(id);
        gatesType.setType(type);
        return ResponseEntity.ok(gatesType);
    }

    @Operation(summary = "Update news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/main/{id}")
    public ResponseEntity<News> changeNewsMainPhoto(@PathVariable int id, @RequestParam("file") MultipartFile file){
        News news  = newsService.findById(id);
        photoConfig.savePhoto(file);
        news.setMain_photo(photoConfig.getPath()+"/"+file.getOriginalFilename());
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/second/{id}")
    public ResponseEntity<News> changeNewsSecondPhoto(@PathVariable int id, @RequestParam("file") MultipartFile file){
        News news  = newsService.findById(id);
        photoConfig.savePhoto(file);
        news.setSecond_photo(photoConfig.getPath()+"/"+file.getOriginalFilename());
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/desc/{id}")
    public ResponseEntity<News> changeNewsDesc(@PathVariable int id, @RequestParam String description){
        News news  = newsService.findById(id);
        news.setDescription(description);
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/header/{id}")
    public ResponseEntity<News> changeNewsHeader(@PathVariable int id, @RequestParam String header){
        News news  = newsService.findById(id);
        news.setHeader(header);
        newsService.save(news);
        return ResponseEntity.ok(news);
    }

    @Operation(summary = "Update review", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDto.class))))})
    @PutMapping("/review/change/{id}")
    public ResponseEntity<Review>changeReview(@PathVariable int id, @RequestBody ReviewUpdateDto reviewUpdateDto){
        Review review = additionalService.findReviewById(id);
        if(reviewUpdateDto.getName() != null  && !reviewUpdateDto.getName().isEmpty() ){
            review.setName(reviewUpdateDto.getName());
        }
        if(reviewUpdateDto.getText() != null && !reviewUpdateDto.getText().isEmpty()){
            review.setText(reviewUpdateDto.getText());
        }
        additionalService.saveReview(review);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/advantages/save")
    public Advantages saveAdvantages(@RequestParam int gates_id , @RequestBody Advantages advantages){
        advantages.setGatesType(gatesService.findTypeById(gates_id));
        return additionalService.saveAdvantages(advantages);
    }


    public News convertToNews(NewsDto newsDto) {
        return this.modelMapper.map(newsDto, News.class);
    }
    public Review convertToReview(ReviewDto reviewDto){
        return this.modelMapper.map(reviewDto, Review.class);
    }
    public Order convertToOrder(OrdersDto orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }


}
