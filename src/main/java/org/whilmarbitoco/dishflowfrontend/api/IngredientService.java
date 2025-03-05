package org.whilmarbitoco.dishflowfrontend.api;


import okhttp3.Request;
import okhttp3.Response;
import org.whilmarbitoco.dishflowfrontend.core.dto.IngredientDTO;
import org.whilmarbitoco.dishflowfrontend.model.Ingredient;

import java.time.LocalDate;
import java.util.List;

public class IngredientService extends Service {

    public IngredientService() {
        super("/ingredient");
    }


    public List<Ingredient> all() throws Exception {
        Request request = new Request.Builder().url(endpoint + "/all").get().build();

        try (Response res = client.newCall(request).execute()) {
            String body = res.body().string();
            if (!res.isSuccessful()) throw new RuntimeException(body);

            List<IngredientDTO> list = mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, IngredientDTO.class));
            return list.stream().map(i -> {
                        return new Ingredient(i.id, i.name, i.quantity,i.unit, LocalDate.parse(i.created_at), LocalDate.parse(i.updated_at));
                    }).toList();
        }
    }

    public List<Ingredient> getMenuIngredients(int id) throws Exception {
        Request request = new Request.Builder() .url(endpoint + "?mid=" + id).get().build();

        try (Response res = client.newCall(request).execute()) {
            String body = res.body().string();
            if (!res.isSuccessful()) {
                throw new RuntimeException(body);
            }
            List<IngredientDTO> list = mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, IngredientDTO.class));
            return list.stream()
                    .map(i -> {
                        return new Ingredient(i.id, i.name, i.quantity,i.unit, LocalDate.parse(i.created_at), LocalDate.parse(i.updated_at));
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
