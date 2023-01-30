package handlers;

import me.aj.productservice.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseHandler {
    public static ResponseEntity<Response> generateResponse(String message, HttpStatus status, Object responseObj) {
       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", null);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj); */

        Response res = Response.builder().data(responseObj).status(status.value()).message(message).timestamp(new Date()).build();

        return new ResponseEntity<Response>(res, status);
    }
}