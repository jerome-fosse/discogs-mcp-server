package com.jf.mcp.discogs.api;

import java.util.Optional;
import java.util.function.Consumer;

public class MasterReleaseVersionsParams {
    private Integer page;
    private Integer pageSize;
    private String format;
    private String label;
    private Integer released;
    private String country;
    private SortingField sort;
    private SortingOrder order;

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }

    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }

    public Optional<String> getFormat() {
        return Optional.ofNullable(format);
    }

    public Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    public Optional<Integer> getReleased() {
        return Optional.ofNullable(released);
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    public Optional<SortingField> getSort() {
        return Optional.ofNullable(sort);
    }

    public Optional<SortingOrder> getOrder() {
        return Optional.ofNullable(order);
    }

    public static MasterReleaseVersionsParams build(Consumer<Builder> consumer) {
        var builder = new Builder();
        consumer.accept(builder);
        return builder.apply();
    }

    public static class Builder {
        private Integer page;
        private Integer pageSize;
        private String format;
        private String label;
        private Integer released;
        private String country;
        private SortingField sort;
        private SortingOrder order;


        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder released(Integer released) {
            this.released = released;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder sort(SortingField sort) {
            this.sort = sort;
            return this;
        }

        public Builder order(SortingOrder order) {
            this.order = order;
            return this;
        }

        private MasterReleaseVersionsParams apply() {
            MasterReleaseVersionsParams params = new MasterReleaseVersionsParams();
            params.page = this.page;
            params.pageSize = this.pageSize;
            params.format = this.format;
            params.label = this.label;
            params.released = this.released;
            params.country = this.country;
            params.sort = this.sort;
            params.order = this.order;
            return params;
        }
    }
}
