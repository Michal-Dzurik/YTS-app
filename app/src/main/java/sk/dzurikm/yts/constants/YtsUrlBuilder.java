package sk.dzurikm.yts.constants;

public class YtsUrlBuilder {
    private ResponseType responseType;
    private String apiRoute;
    private RequestParameters parameters;

    public YtsUrlBuilder() {
    }

    public YtsUrlBuilder(ResponseType responseType, String apiRoute) {
        this.responseType = responseType;
        this.apiRoute = apiRoute;
    }

    public YtsUrlBuilder(ResponseType responseType) {
        this.responseType = responseType;
    }

    public YtsUrlBuilder clear(){
        this.apiRoute = null;
        this.parameters = null;
        this.responseType = null;

        return this;
    }

    public YtsUrlBuilder setParameters(RequestParameters parameters){
        this.parameters = parameters;

        return this;
    }

    public YtsUrlBuilder setApiRoute(String route){
        this.apiRoute = route;

        return this;
    }

    public String getUrl(){
        if (this.apiRoute == null || parameters == null || responseType == null) return "";
        return ApiMap.BASE_URL + "/" + apiRoute + "." + responseType.toString().toLowerCase() + (parameters.toString().equals("") ? "" : "?" + parameters.toString());
    }


}