package com.hilton.job.model.request;

public class JobRequest {
    private Object variables = new Object();
    private String query;

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        StringBuilder sb = new StringBuilder();
        sb.append("{jobs(input: {type: \"");
        sb.append(query);
        sb.append("\"}) {title company {name logoUrl} description}}");
        this.query = sb.toString();
    }
}
