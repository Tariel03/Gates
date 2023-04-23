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
    AdditionalService additionalService;
    RegistrationService registrationService;
    //No need for Autowiring!
    PhotoConfig photoConfig = new PhotoConfig();
    @Autowired
    public AdminController(JwtUtil jwtUtil, GatesService gatesService, AuthenticationManager authenticationManager, DoneService doneService, ModelMapper modelMapper, NewsService newsService, OrderService orderService, ChangeService changeService,  AdditionalService additionalService, RegistrationService registrationService) {
        this.jwtUtil = jwtUtil;
        this.gatesService = gatesService;
        this.authenticationManager = authenticationManager;
        this.doneService = doneService;
        this.modelMapper = modelMapper;
        this.newsService = newsService;
        this.orderService = orderService;
        this.changeService = changeService;
        this.additionalService = additionalService;
        this.registrationService = registrationService;
    }

    @PostMapping("/gates/save")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Save gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<Gates> saveGates(@RequestParam String description,@RequestParam String header, @RequestParam("file") MultipartFile multipartFile ) {
        Gates gates = new Gates();
        gates.setDescription(description);
        gates.setHeader(header);
        photoConfig.savePhoto(multipartFile);
        gates.setLink(multipartFile.getOriginalFilename());
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }
    @DeleteMapping("/gates/delete")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Delete gates", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<HttpStatus> deleteGates(@RequestParam int id) {
        gatesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/gates/kill")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Delete gates", description = "This request makes a new order")
    public ResponseEntity<HttpStatus> killGates(@RequestParam int id){
        gatesService.setDead(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Done save", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Done.class))))})
    @PostMapping("/done/save")
    public ResponseEntity<Done> saveDone( @RequestParam("file") MultipartFile file){
        Done done = new Done();
        photoConfig.savePhoto(file);
        done.setLink(file.getOriginalFilename());
        doneService.save(done);
        return ResponseEntity.ok(done);
    }
    @Operation(summary = "Delete done", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Done.class))))})
    @DeleteMapping("/done/delete")
    public ResponseEntity<HttpStatus> deleteDone( @RequestParam int id){
        doneService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/news/save")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Write news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class)))) })
    public ResponseEntity<News> saveNews(@RequestParam String description, @RequestParam String header, @RequestParam("file1") MultipartFile file, @RequestParam("file2") MultipartFile multipartFile){

//        News news = new News();
        News news = new News();
        news.setDate(LocalDate.now());
        news.setHeader(header);
        news.setDescription(description);
        photoConfig.savePhoto(file);
        photoConfig.savePhoto(multipartFile);
        news.setMain_photo(file.getOriginalFilename());
        news.setSecond_photo(multipartFile.getOriginalFilename());
        news.setAdmin(registrationService.currentUser());

        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Delete comment", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @DeleteMapping("/news/delete")
    public ResponseEntity<HttpStatus> deleteNews(@RequestParam  int id){
        newsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Operation(summary = "Write review", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @PostMapping("/review/save")
    public ResponseEntity<Review> saveReview(@RequestParam String name, @RequestParam String text ,@RequestParam("file") MultipartFile file){
        Review review   = new Review();
        review.setName(name);
        review.setText(text);
        photoConfig.savePhoto(file);
        review.setLink(file.getOriginalFilename());
        additionalService.saveReview(review);
        return ResponseEntity.ok(review);
    }


    @GetMapping("/changes")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public List<Changes> allChanges(){
        return changeService.allChanges();
    }


    @GetMapping("/changes/{id}")
//    @CrossOrigin(origins = {"http://localhost:3000", "*"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public ResponseEntity<Changes> findById(@PathVariable int id ){
        return ResponseEntity.ok(changeService.findById(id));
    }
    @Operation(summary = "Update an order", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderUpdateDto.class))))})
    @PutMapping("/order/change")
    public ResponseEntity<String> update(@RequestParam int id){
        Order order = orderService.findById(id).get();
        order.setAdmin(registrationService.currentUser());
        orderService.updatedStatus(order, "updated");
        orderService.save(order);
        Changes changes = changeService.generate(order, order.getStatus(), registrationService.currentUser());
        changeService.save(changes);
        return ResponseEntity.ok("Order status has been changed");
    }
//    @Operation(summary = "Save new gates_type", description = "This request makes a new order")
////    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation",
//                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
//    @PostMapping("gates_types/save")
//    public ResponseEntity<String> saveType(@RequestParam String type, @RequestParam("file")MultipartFile file){
//
//        gatesType gatesType = new gatesType();
//        gatesType.setType(type);
//        photoConfig.savePhoto(file);
//        gatesType.setLink("http://161.35.29.179:8085/"+photoConfig.getPath()+"/"+file.getOriginalFilename());
//        gatesService.save(gatesType);
//        return ResponseEntity.ok("Successfully created!");
//    }
    @Operation(summary = "Update gates", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PutMapping("/gates/change/description")
    public ResponseEntity<Gates>changeDesc(@RequestParam int id, @RequestParam String description){
        Gates gates = gatesService.findById(id).get();
        gates.setDescription(description);
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }

    @Operation(summary = "Update gates", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    @PutMapping("/gates/change/photo")
    public ResponseEntity<Gates>changePhoto(@RequestParam int id, @RequestParam("file") MultipartFile multipartFile){
        Gates gates = gatesService.findById(id).get();
        photoConfig.savePhoto(multipartFile);
        gates.setLink(multipartFile.getOriginalFilename());
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }
//    @Operation(summary = "Change photo", description = "This request makes a new order")
////    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation",
//                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = gatesType.class))))})
//    @PutMapping("/gates_type/change/photo/{id}")
//    public ResponseEntity<gatesType>changeType(@PathVariable int id, @RequestParam("file")MultipartFile file){
//        gatesType gatesType = gatesService.findTypeById(id);
//        photoConfig.savePhoto(file);
//        gatesType.setLink(photoConfig.getPath()+"/"+file.getOriginalFilename());
//        gatesService.save(gatesType);
//        return ResponseEntity.ok(gatesType);
//    }
//    @Operation(summary = "Change type ", description = "This request makes a new order")
////    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation",
//                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = gatesType.class))))})
//    @PutMapping("/gates_type/change/type/{id}")
//    public ResponseEntity<gatesType>changeTypePhoto(@PathVariable int id, @RequestParam("type")String type){
//        gatesType gatesType = gatesService.findTypeById(id);
//        gatesType.setType(type);
//        return ResponseEntity.ok(gatesType);
//    }

    @Operation(summary = "Update news", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/main")
    public ResponseEntity<News> changeNewsMainPhoto(@RequestParam int id, @RequestParam("file") MultipartFile file){
        News news  = newsService.findById(id);
        photoConfig.savePhoto(file);
        news.setMain_photo(file.getOriginalFilename());
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/second")
    public ResponseEntity<News> changeNewsSecondPhoto(@RequestParam int id, @RequestParam("file") MultipartFile file){
        News news  = newsService.findById(id);
        photoConfig.savePhoto(file);
        news.setSecond_photo(file.getOriginalFilename());
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class))))})
    @PutMapping("/news/change/desc")
    public ResponseEntity<News> changeNewsDesc(@RequestParam int id, @RequestParam String description){
        News news  = newsService.findById(id);
        news.setDescription(description);
        newsService.save(news);
        return ResponseEntity.ok(news);
    }
    @Operation(summary = "Update news", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @PutMapping("/news/change/header")
    public ResponseEntity<News> changeNewsHeader(@RequestParam int id, @RequestParam String header){
        News news  = newsService.findById(id);
        news.setHeader(header);
        newsService.save(news);
        return ResponseEntity.ok(news);
    }

    @Operation(summary = "Update review", description = "This request makes a new order")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
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
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public Advantages saveAdvantages(@RequestParam int gates_id , @RequestBody Advantages advantages){
        advantages.setGates(gatesService.findById(gates_id).get());
        return additionalService.saveAdvantages(advantages);
    }
    @GetMapping("/orders")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    public List<Order> findAll(){
        return orderService.findAll();
    }
    @GetMapping("/orders/{id}")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public Order getOne(@PathVariable int id){
        return orderService.findById(id).orElse(null);
    }



    public News convertToNews(NewsDto newsDto) {
        return this.modelMapper.map(newsDto, News.class);
    }
    public NewsDto convertToNewsDto(News news){return this.modelMapper.map(news, NewsDto.class);}
    public Review convertToReview(ReviewDto reviewDto){
        return this.modelMapper.map(reviewDto, Review.class);
    }
    public Order convertToOrder(OrdersDto orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }


}
