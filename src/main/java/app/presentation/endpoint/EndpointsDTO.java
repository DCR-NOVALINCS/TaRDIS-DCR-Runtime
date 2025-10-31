package app.presentation.endpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record EndpointsDTO(Map<String, EndpointDTO> endpoints) {

    @JsonCreator
    public EndpointsDTO(List<EndpointDTO> endpoints) {
        this((endpoints != null && !endpoints.isEmpty())
                ? endpoints.stream()
                .collect(Collectors.toMap(endpoint -> endpoint.role().label(),
                        Function.identity()))
                : Collections.emptyMap());
    }
}
