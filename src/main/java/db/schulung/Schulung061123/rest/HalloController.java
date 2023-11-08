package db.schulung.Schulung061123.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalloController {

    @GetMapping("hallo/{name}")
    String sayHello(
            @PathVariable String name,
            @RequestParam int n) {
        return "moin ".repeat(n) + name;
    }
}
