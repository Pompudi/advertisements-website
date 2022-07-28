package our.replacement.store.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import our.replacement.store.service.ProductServiceImpl;

import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ResourceBundle resourceBundle;

    private final Logger logger;

    public GlobalExceptionHandler() {
        this.resourceBundle = ResourceBundle.getBundle("static/bundle/ExceptionsBundle_ru");
        this.logger = LogManager.getLogger(ProductServiceImpl.class);
    }

    @ExceptionHandler(InternalServerException.class)
    public String handleInternalServerException(InternalServerException e, Model model) {
        logger.fatal("InternalServerException happened");
        model.addAttribute("message", resourceBundle.getString("InternalServerException.message"));
        return "exception/exception";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherException(Exception e, Model model) {
        logger.error("Exception type {} happened", e.getClass().getSimpleName());
        model.addAttribute("message", resourceBundle.getString("OtherException.message"));
       return "exception/exception";
   }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e, Model model) {
        logger.error("Entity with type {} and id {} not found", e.getClassname(), e.getEntityId());
        String messageFromBundle = resourceBundle.getString("EntityNotFoundException.message");
        String message = String.format(messageFromBundle, e.getClassname(), e.getEntityId());
        model.addAttribute("message", message);
        return "exception/exception";
    }
}
