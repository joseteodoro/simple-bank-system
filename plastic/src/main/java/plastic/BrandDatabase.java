package plastic;

import java.util.List;

public class BrandDatabase {

    public List<Brand> listBrandRanges() {
        return List.of(
            Brand.of("American Express", "34000000", "34999999"),
            Brand.of("American Express", "37000000", "37999999"),
            Brand.of("Diners", "30000000", "30099999"),
            Brand.of("Diners", "30100000", "30199999"),
            Brand.of("Diners", "30200000", "30299999"),
            Brand.of("Diners", "30300000", "30399999"),
            Brand.of("Diners", "36000000", "36999999"),
            Brand.of("Diners", "38000000", "38999999"),
            Brand.of("Discover", "60110000", "60119999"),
            Brand.of("Enroute", "20140000", "20149999"),
            Brand.of("Enroute", "21490000", "21499999"),
            Brand.of("JCB", "21000000", "21009999"),
            Brand.of("JCB", "18000000", "18009999"),
            Brand.of("JCB", "30880000", "30889999"),
            Brand.of("JCB", "30960000", "30969999"),
            Brand.of("JCB", "31120000", "31129999"),
            Brand.of("JCB", "31580000", "31589999"),
            Brand.of("JCB", "33370000", "33379999"),
            Brand.of("JCB", "35280000", "35289999"),
            Brand.of("MasterCard", "51000000", "51999999"),
            Brand.of("MasterCard", "52000000", "52999999"),
            Brand.of("MasterCard", "53000000", "53999999"),
            Brand.of("MasterCard", "54000000", "54999999"),
            Brand.of("MasterCard", "55000000", "55999999"),
            Brand.of("Visa", "45390000", "45399999"),
            Brand.of("Visa", "45560000", "45569999"),
            Brand.of("Visa", "49160000", "49169999"),
            Brand.of("Visa", "45320000", "45329999"),
            Brand.of("Visa", "49290000", "49299999"),
            Brand.of("Visa", "44850000", "44859999"),
            Brand.of("Visa", "47160000", "47169999"),
            Brand.of("Visa", "40240071", "40240071"),
            Brand.of("Voyager", "86990000", "86999999")
        );
    }
    
}
