package tag;

import jakarta.servlet.jsp.tagext.TagSupport;

public class Submit extends TagSupport {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() {
        try {
            switch (type){
                case "ok":
                    pageContext.getOut().write("<input type='submit' value='Ok'/>");
                    break;
                default:
                    pageContext.getOut().write("<input type='reset' value='Cancel'/>");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return SKIP_BODY;
    }
}
