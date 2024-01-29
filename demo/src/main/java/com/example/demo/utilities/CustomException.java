package com.example.demo.utilities;

//@ControllerAdvice
//public class CustomException {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ResponseEntity<List<String>> processUnmergeException(final MethodArgumentNotValidException ex) {
//
//        List<String> list = ex.getBindingResult().getAllErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
//    }
//}
