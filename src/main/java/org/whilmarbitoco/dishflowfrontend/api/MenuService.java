package org.whilmarbitoco.dishflowfrontend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.whilmarbitoco.dishflowfrontend.core.dto.ErrorDTO;
import org.whilmarbitoco.dishflowfrontend.core.dto.IngredientDTO;
import org.whilmarbitoco.dishflowfrontend.core.dto.MenuDTO;
import org.whilmarbitoco.dishflowfrontend.core.dto.ResultDTO;
import org.whilmarbitoco.dishflowfrontend.model.Ingredient;
import org.whilmarbitoco.dishflowfrontend.model.Menu;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MenuService {

    Http httpService;
    ObjectMapper mapper;
    OkHttpClient client;

    public MenuService() {
        this.httpService = new HttpService();
        this.mapper = new ObjectMapper();
        this.client = new OkHttpClient();;
    }

    public List<Menu> all() throws Exception {
        String res = httpService.get("/menu/all");
        List<MenuDTO> menuItems = mapper.readValue(res, mapper.getTypeFactory().constructCollectionType(List.class, MenuDTO.class));
        return menuItems.stream().map(m -> {
            return new Menu(m.getId(), m.getName(), m.getPrice(), m.getDescription(), m.getImg(), m.isAvailable(), m.getDescription(), m.getType());
        }).toList();
    }

    public void delete(int id) throws Exception {
        httpService.delete("/menu/delete?id=" + id);
    }

    public String create(String name, String price, String description, String type, String filePath) throws Exception {
        if (name.isEmpty() || price.isEmpty() || description.isEmpty() || type.isEmpty() || filePath.isEmpty()) {
            throw new RuntimeException("Fields cannot be empty");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("price", price)
                .addFormDataPart("description", description)
                .addFormDataPart("type", type)
                .addFormDataPart("image", file.getName(),
                RequestBody.create(file, MediaType.parse("image/jpeg")))
                .build();

        Request request = new Request.Builder()
                .url(HttpService.BASE_URL + "/menu/add")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                ResultDTO res = mapper.readValue(response.body().string(), ResultDTO.class);
                return res.result;
            }

            ErrorDTO res = mapper.readValue(response.body().string(), ErrorDTO.class);
            throw new RuntimeException(res.error);
        }
    }

    public List<Ingredient> getIngredients(int id) throws Exception {

        Request request = new Request.Builder()
                .url(HttpService.BASE_URL + "/ingredient?mid=" + id)
                .get()
                .build();


        try (Response res = client.newCall(request).execute()) {
            if (!res.isSuccessful()) {
                throw new RuntimeException("Unable to retrieve ingredient");
            }

            String body = res.body().string();

            List<IngredientDTO> list = mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, IngredientDTO.class));
            return list.stream()
                    .map(i -> {
                        return new Ingredient(i.id, i.name, i.quantity,i.unit, LocalDate.parse(i.created_at), LocalDate.parse(i.updated_at));
                    }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
