import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PipeAndFilter {
    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // Create a pipeline
        List<Function<List<Integer>, List<Integer>>> filters = new ArrayList<>();
        filters.add(PipeAndFilter::filterEvenNumbers);
        filters.add(PipeAndFilter::squareNumbers);
        filters.add(PipeAndFilter::filterNumbersGreaterThanTen);
        filters.add(PipeAndFilter::filterNumbersGreaterThan50);
        filters.add(PipeAndFilter::cubeNumbers);
        // Process the input through the pipeline
        List<Integer> result = processPipeline(input, filters);
        // Output the result
        System.out.println("final result : " + result);
    }

    // Process the input through the pipeline of filters
    private static List<Integer> processPipeline(List<Integer> input,
            List<Function<List<Integer>, List<Integer>>> filters) {
        List<Integer> output = input;
        for (Function<List<Integer>, List<Integer>> filter : filters) {
            output = filter.apply(output);
        }
        return output;
    }


    private static List<Integer> filterEvenNumbers(List<Integer> input) {
        var tempResult = input.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        printResult("Even Filter", tempResult);
        return tempResult;
    }

    private static void printResult(String filterType, List<Integer> tempResult) {
        System.out.println(filterType + " " + tempResult.toString());
    }

    private static List<Integer> squareNumbers(List<Integer> input) {
        return input.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    private static List<Integer> filterNumbersGreaterThanTen(List<Integer> input) {
        return input.stream()
                .filter(n -> n > 10)
                .collect(Collectors.toList());
    }


    private static List<Integer> filterNumbersGreaterThan50(List<Integer> input) {
        var greater50 = input.stream()
                .filter(n -> n > 50)
                .collect(Collectors.toList());
        PrintResult("greater than 50 : ", greater50);
        return greater50;
    }

    private static void PrintResult(String filerType, List<Integer> greater50) {

        System.out.println(filerType + " " + greater50.toString());
    }

    private static List<Integer> cubeNumbers(List<Integer> input) {
        return input.stream()
                .map(n -> n * n * n)
                .collect(Collectors.toList());
    }

}
