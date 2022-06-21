package com.bm.membership.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.bm.membership.component
 * fileName       : JsonMapper
 * author         : men16
 * date           : 2022-06-20
 * description    : Json 처리 Component
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@Component
public class JsonUserComponent {

    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String objectToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);

    }


    /**
     * @param <E>
     * @param json
     * @param clazz
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public <E> E jsonToObject(String json, Class<E> clazz) throws JsonMappingException, JsonProcessingException {
        return this.objectMapper.readValue(json, clazz);

    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> jsonToMap(String json) throws JsonMappingException, JsonProcessingException {
        return this.objectMapper.readValue(json, Map.class);

    }


    /**
     * @param <E>
     * @param json
     * @param clazz
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public <E> List<E> jsonToObjectList(String json, Class<E> clazz) throws JsonMappingException, JsonProcessingException {
        return Arrays.asList(this.objectMapper.readValue(json, clazz));

    }

}

