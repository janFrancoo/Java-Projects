package com.janfranco.currency_exchange.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    /*@GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleAPI() {
        return "Sample API";
    }

    public String hardcodedResponse(Exception exception) {
        return "Fallback";
    }*/
}
