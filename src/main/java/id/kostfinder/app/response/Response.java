package id.kostfinder.app.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
        private String rc;
        private String message;
        private Object errTech;
        private String source;
        private Object data;
}
