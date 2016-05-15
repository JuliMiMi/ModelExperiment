package modeling.ui.view.indicator;

/**
 * Created by Oleksii Martyniuk on 15.05.2016.
 */
public class CompanyModel {

    private Boolean valid = Boolean.FALSE;

    private String name;
    private Double netWorth;
    private Double contributionsBudgetSocial;
    private Double advancedResources;
    private Double resourcesUsed;
    private Double costJobs;
    private Double profitableMarketCapacity;
    private Double grossIncomeWorker;
    private Double grossIncomeGeneral;
    private Double periodTheResultingGross;
    private Double periodGrossHalf;

    private Double indRes;
    private Double indWork;
    private Double indMar;
    private Double indRent;
    private Double indTime;

    public void calc() {
        indRes = (netWorth / contributionsBudgetSocial) / (advancedResources / resourcesUsed);
        indWork = netWorth / costJobs;
        indMar = netWorth / profitableMarketCapacity;
        indRent = grossIncomeWorker / grossIncomeGeneral;
        indTime = periodTheResultingGross / periodGrossHalf;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    public Double getContributionsBudgetSocial() {
        return contributionsBudgetSocial;
    }

    public void setContributionsBudgetSocial(Double contributionsBudgetSocial) {
        this.contributionsBudgetSocial = contributionsBudgetSocial;
    }

    public Double getAdvancedResources() {
        return advancedResources;
    }

    public void setAdvancedResources(Double advancedResources) {
        this.advancedResources = advancedResources;
    }

    public Double getResourcesUsed() {
        return resourcesUsed;
    }

    public void setResourcesUsed(Double resourcesUsed) {
        this.resourcesUsed = resourcesUsed;
    }

    public Double getCostJobs() {
        return costJobs;
    }

    public void setCostJobs(Double costJobs) {
        this.costJobs = costJobs;
    }

    public Double getProfitableMarketCapacity() {
        return profitableMarketCapacity;
    }

    public void setProfitableMarketCapacity(Double profitableMarketCapacity) {
        this.profitableMarketCapacity = profitableMarketCapacity;
    }

    public Double getGrossIncomeWorker() {
        return grossIncomeWorker;
    }

    public void setGrossIncomeWorker(Double grossIncomeWorker) {
        this.grossIncomeWorker = grossIncomeWorker;
    }

    public Double getGrossIncomeGeneral() {
        return grossIncomeGeneral;
    }

    public void setGrossIncomeGeneral(Double grossIncomeGeneral) {
        this.grossIncomeGeneral = grossIncomeGeneral;
    }

    public Double getPeriodTheResultingGross() {
        return periodTheResultingGross;
    }

    public void setPeriodTheResultingGross(Double periodTheResultingGross) {
        this.periodTheResultingGross = periodTheResultingGross;
    }

    public Double getPeriodGrossHalf() {
        return periodGrossHalf;
    }

    public void setPeriodGrossHalf(Double periodGrossHalf) {
        this.periodGrossHalf = periodGrossHalf;
    }

    public Double getIndRes() {
        return indRes;
    }

    public void setIndRes(Double indRes) {
        this.indRes = indRes;
    }

    public Double getIndWork() {
        return indWork;
    }

    public void setIndWork(Double indWork) {
        this.indWork = indWork;
    }

    public Double getIndMar() {
        return indMar;
    }

    public void setIndMar(Double indMar) {
        this.indMar = indMar;
    }

    public Double getIndRent() {
        return indRent;
    }

    public void setIndRent(Double indRent) {
        this.indRent = indRent;
    }

    public Double getIndTime() {
        return indTime;
    }

    public void setIndTime(Double indTime) {
        this.indTime = indTime;
    }
}
