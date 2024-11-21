package mr.demonid.spring.hw2.controller;

import mr.demonid.spring.hw2.model.User;
import mr.demonid.spring.hw2.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final IService userService;

    public UserController(IService userService) {
        this.userService = userService;
    }

    /**
     * Просто перенаправляем на главную страницу сайта
     * Чтобы не вводить /users в адресной строке
     */
    @GetMapping("/")
    public String toHome()
    {
        return "redirect:/users";
    }

    /**
     * GET: Подготовка и возврат страницы со списком пользователей
     * @param model интерфейс взаимодействия с Thymeleaf
     * @return html-макет для Thymeleaf (он и вернет подготовленную по макету страницу HTML)
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
//        System.out.println("GET /users: " + users);
        model.addAttribute("users", users);             // передаём Thymeleaf список пользователей
        return "user-list";
    }

    /**
     * GET: Запрос формы для заполнения полей о новом юзере
     * @param user экземпляр пользователя, который будет связан с формой (th:object="${user}")
     * @return html-макет с формой для создания нового пользователя.
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * POST: Отправка данных на сервер (то есть приход данных к нам), в данном случае отправляем их в БД
     * @param user Данные из формы создания юзера
     * @return Переход на страницу /users
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * GET: Перехват команды на удаление пользователя
     * @param id Уникальный идентификатор пользователя
     * @return Переход на страницу /users
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id)
    {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * GET: Запрос формы для изменения данных о пользователе
     * @param model интерфейс взаимодействия с Thymeleaf
     * @param id    Уникальный идентификатор пользователя
     * @return html-макет с формой для редактирования данных пользователя
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(Model model, @PathVariable("id") int id)
    {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Отправка данных из формы на сервер (то есть приход данных к нам), то есть отправляем в БД
     * @param user Новые данные пользователя из формы
     * @return Переход на страницу /users
     */
    @PostMapping("/user-update")
    public String updateUser(User user)
    {
        userService.update(user);
        return "redirect:/users";
    }
}
