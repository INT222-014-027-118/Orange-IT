package INT222.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundImageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundImageName(NotFoundImageException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameImageException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String SameImage(SameImageException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFound(NotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameProductNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String productSameName(SameProductNameException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundNameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundName(NotFoundNameException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundDeliveryDetailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundDeliveryDetail(NotFoundDeliveryDetailException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameColorNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String colorSameName(SameColorNameException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameColorHexException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String colorSameHexCode(SameColorHexException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundColorIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundColor(NotFoundColorIdException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameRoleException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String sameRoleName(SameRoleException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundCartItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundCartItem(NotFoundCartItemException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundCategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundCategory(NotFoundCategoryException ex){
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(SameCategoryException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String sameCategory(SameCategoryException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductActiveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String productActive(ProductActiveException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DiscountActiveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String DiscountActive(DiscountActiveException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundDiscountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundDiscount(NotFoundDiscountException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundRatingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundRating(NotFoundRatingException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundAttributeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundAttribute(NotFoundAttributeException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SameAttributeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String SameAttribute(SameAttributeException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundUserCartItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundUserCartItem(NotFoundUserCartItemException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundOrderException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundOrder(NotFoundOrderException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundOrderItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundOrder(NotFoundOrderItemException ex){
        return ex.getMessage();
    }


}
