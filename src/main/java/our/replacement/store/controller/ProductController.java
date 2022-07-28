package our.replacement.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import our.replacement.store.dto.*;
import our.replacement.store.security.UserDetailsImpl;
import our.replacement.store.service.CategoryService;
import our.replacement.store.service.ProductService;
import our.replacement.store.service.UserDetailServiceImpl;

import javax.validation.Valid;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("v1/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, UserDetailServiceImpl userDetailService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (userDetails != null){
            model.addAttribute("userId", userDetails.getUser().getUserId());
        }

        ProductDto productDto = productService.getProduct(productId);
        model.addAttribute("productDto", productDto);
        return "product/product";
    }

    @GetMapping("/create")
    public String getFormCreateProduct(Model model) {
        ProductDto emptyProductDto = new ProductDto();
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        model.addAttribute("productDto", emptyProductDto);
        model.addAttribute("categoryDtoList", categoryDtoList);

        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        model.addAttribute("userId", userDetails.getUser().getUserId());

        return "product/form_creation";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute(name = "productDto") @Valid ProductDto productDto,
                                BindingResult bindingResult, @ModelAttribute MultipartFile imageFile, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
            model.addAttribute("productDto", productDto);
            model.addAttribute("categoryDtoList", categoryDtoList);
            return "product/form_creation";
        }
        ProductDto savedProductDto = productService.createProduct(productDto, imageFile);
        return "redirect:/v1/product/" + savedProductDto.getProductId();
    }

    @GetMapping("/{productId}/edit")
    public String getFormEditProduct(@PathVariable(name = "productId") Long productId, Model model) {
        ProductDto productDto = productService.getProduct(productId);
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (Objects.equals(userDetails.getUser().getUserId(), productDto.getSellerId())) {

            model.addAttribute("productDto", productDto);
            model.addAttribute("categoryDtoList", categoryDtoList);

            return "product/form_edition";
        }
        return "exception/permission_denied";
    }

    @PutMapping("/{productId}/edit")
    public String editProduct(@PathVariable(name = "productId") Long productId, @ModelAttribute MultipartFile imageFile,
                              @ModelAttribute @Valid ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
            model.addAttribute("productDto", productDto);
            model.addAttribute("categoryDtoList", categoryDtoList);
            return "product/form_edition";
        }
        ProductDto savedProduct = productService.updateProduct(productId, productDto, imageFile);
        return "redirect:/v1/product/" + savedProduct.getProductId();
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable(name = "productId") Long productId, ProductDto productDto) {
        productService.deleteProduct(productId);
        return "redirect:/v1/user/" + productDto.getSellerId();
    }

    @GetMapping("/search")
    public String getFormSearch(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        model.addAttribute("categoryDtoList", categoryDtoList);

        FilterDto filterName = new FilterDto("name", FilterDto.Operation.ILIKE, Types.VARCHAR);
        FilterDto filterCategory = new FilterDto("category_id", FilterDto.Operation.EQUAL, Types.BIGINT);
        FilterDtoWrapper wrapper = new FilterDtoWrapper(filterName, filterCategory);

        model.addAttribute("filterWrapper", wrapper);
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getUser().getUserId());
        }
        return "product/search";
    }

    @PostMapping("/search")
    public String searchProducts(@ModelAttribute FilterDtoWrapper filterDtoWrapper, Model model) {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        List<FilterDto> filterDtoList = List.of(filterDtoWrapper.getFilterName(), filterDtoWrapper.getFilterCategory());

        model.addAttribute("categoryDtoList", categoryDtoList);

        List<ShortProductDto> shortProductDtoList = productService.search(filterDtoList);

        model.addAttribute("shortProductDtoList", shortProductDtoList);
        model.addAttribute("filterWrapper", filterDtoWrapper);

        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getUser().getUserId());
        }

        return "product/search";
    }

    @PostMapping("/deal")
    public String createDeal(@ModelAttribute ProductDto productDto) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (!Objects.equals(userDetails.getUser().getUserId(), productDto.getSellerId())) {
            productService.createDeal(productDto);
            return "product/deal_completed";
        }
        return "exception/permission_denied";
    }
}
