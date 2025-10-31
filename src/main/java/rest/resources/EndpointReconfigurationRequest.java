package rest.resources;

import java.util.Map;
import app.presentation.endpoint.EndpointDTO;

//public record EndpointReconfigurationRequest(UserVal self, GraphElement graphElement) {
public record EndpointReconfigurationRequest(
        Map<String, EndpointDTO> endpoints) {
}
