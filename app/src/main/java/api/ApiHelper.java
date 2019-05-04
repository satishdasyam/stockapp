package api;


import com.intrinio.api.CompanyApi;
import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.ApiResponseCompanies;
import com.intrinio.models.ApiResponseSecurityStockPrices;

import org.threeten.bp.LocalDate;

public class ApiHelper {

    public ApiHelper() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("OmY4ZTEzMDFjOGYwY2Q4NjMzYzE1MTZmMzk5ODE2OTY5");

    }

    private void getAllCompanies() {
        CompanyApi companyApi = new CompanyApi();

        LocalDate latestFilingDate = null; // LocalDate | Last filing date
        String sic = null; // String | Standard Industrial Classification code
        String template = null; // String | Template
        String sector = null; // String | Industry sector
        String industryCategory = null; // String | Industry category
        String industryGroup = null; // String | Industry group
        Integer pageSize = 100; // Integer | The number of results to return
        String nextPage = null; // String | Gets the next page of data from a previous API call

        try {
            ApiResponseCompanies result = companyApi.getAllCompanies(latestFilingDate, sic, template, sector, industryCategory, industryGroup, pageSize, nextPage);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CompanyApi#getAllCompanies");
            e.printStackTrace();
        }
    }

    private void getCompanyDetailsByIdentifier() {

        SecurityApi securityApi = new SecurityApi();
        String identifier = "AAPL"; // String | A Security identifier (Ticker, FIGI, ISIN, CUSIP, Intrinio ID)
        LocalDate startDate = LocalDate.of(2019, 5, 2);
        ;
        // LocalDate | Return prices on or after the date
        LocalDate endDate = LocalDate.of(2019, 5, 3); // LocalDate | Return prices on or before the date
        String frequency = "daily"; // String | Return stock prices in the given frequency
        Integer pageSize = null; // BigDecimal | The number of results to return
        String nextPage = null; // String | Gets the next page of data from a previous API call
        try {
            ApiResponseSecurityStockPrices result = securityApi.getSecurityStockPrices(identifier, startDate, endDate, frequency, pageSize, nextPage);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SecurityApi#getSecurityStockPrices");
            e.printStackTrace();
        }
    }
}

