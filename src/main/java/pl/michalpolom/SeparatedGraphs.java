package pl.michalpolom;

import java.util.*;
import java.util.stream.Collectors;

public class SeparatedGraphs {
    public static void main(String[] args) {
        var input = prepareInput();
        application(input);
    }

    private static ArrayList<ArrayList<Integer>> prepareInput() {
        try {
            ArrayList<ArrayList<Integer>> input = new ArrayList<>();
            Scanner sc = new Scanner(System.in);

            int capacity = sc.nextInt();
            sc.nextLine();

            if (capacity < 1){
                throw new InputMismatchException();
            }

            for (int i = 0; i < capacity; i++) {
                String[] integers = sc.nextLine().split(" ");
                var collect = Arrays.stream(integers)
                        .map(Integer::valueOf)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (collect.size() != 2) {
                    throw new IndexOutOfBoundsException();
                } else if (collect.stream().filter(value -> value > 0).count() != 2) {
                    throw new NumberFormatException();
                }

                input.add(collect);
            }

            return input;
        } catch (NumberFormatException | InputMismatchException e ) {
            throw new NumberFormatException("Arguments should be between 1 and 2147483647");
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Too many values in line");
        }
    }

    private static void application(ArrayList<ArrayList<Integer>> pairs) {
        ArrayList<HashSet<Integer>> graphs = new ArrayList<>();
        graphs.add(new HashSet<>(pairs.get(0))); // graphs init

        boolean existGraphForPairCheck;
        for (List<Integer> pair : pairs.subList(1, pairs.size())) {
            existGraphForPairCheck = false;
            for (Set<Integer> graph : graphs) {
                if (graph.contains(pair.get(0)) || graph.contains(pair.get(1))) {
                    graph.addAll(pair);
                    existGraphForPairCheck = true;
                    break;
                }
            }
            if (!existGraphForPairCheck) {
                graphs.add(new HashSet<>(pair));
            }
        }

        System.out.println(graphs.size());
    }
}