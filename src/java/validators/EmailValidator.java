package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@FacesValidator("validators.EmailValidator")
public class EmailValidator implements Validator {
    
    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    /**
     *
     * @param context
     * @param component
     * @param value
     */
    @Override
    public void validate(FacesContext context,
                        UIComponent component,
                        Object value)  throws ValidatorException {
        try {
            String emailAddress = (String)value;
            
            if (!isValidEmailAddress(emailAddress)) {
                FacesMessage message = new FacesMessage("Email address format is not valid.");
                throw new ValidatorException(message);
            }
            
        } catch (ClassCastException e) {
            FacesMessage message = new FacesMessage("Value must be a a String.");
            throw new ValidatorException(message);
        }
        
    }
    
}
