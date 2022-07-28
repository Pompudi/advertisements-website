package our.replacement.store.dto;

public class FilterDtoWrapper {

    private FilterDto filterName;
    private FilterDto filterCategory;

    public FilterDtoWrapper() {
    }

    public FilterDtoWrapper(FilterDto filterName, FilterDto filterCategory) {
        this.filterName = filterName;
        this.filterCategory = filterCategory;
    }

    public FilterDto getFilterName() {
        return filterName;
    }

    public void setFilterName(FilterDto filterName) {
        this.filterName = filterName;
    }

    public FilterDto getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(FilterDto filterCategory) {
        this.filterCategory = filterCategory;
    }
}
