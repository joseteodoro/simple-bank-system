package plastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PerformanceTest {


    @Test
    public void performanceTest() throws IOException {
        System.out.println(LocalDateTime.now());
        CardService service = CardService.of(new BrandDatabase().listBrandRanges());
        SampleDatabase.largeCardSample().parallelStream()
            .map(str -> service.findBrandName(str))
            .collect(Collectors.toList());
        System.out.println(LocalDateTime.now());
    }

}
