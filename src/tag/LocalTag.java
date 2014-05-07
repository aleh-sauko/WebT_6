package tag;

import manager.MessageManager;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by aleh on 07.05.14.
 */
public class LocalTag extends TagSupport {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public int doStartTag() {
        try {
            pageContext.getOut().write(MessageManager.getInstance().getProperty(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
