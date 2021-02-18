package ru.javaops.masterjava.demo.stream_api_join_filter;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.masterjava.demo.example_model.IncomeUser;
import ru.javaops.masterjava.demo.example_model.MyUser;

import java.util.*;
import java.util.stream.Collectors;

public class MainStreamJoin {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainStreamJoin.class);
    private static final int CNT = 500000;
    public static void main(String[] args) throws Exception {
        LOGGER.info("start");
        List<MyUser> myUserList = new ArrayList<>();
        List<IncomeUser> incomeUserList = new ArrayList<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i <= CNT; i++) {
           // myUserList.add(new MyUser(i, 100 * i));
        }

        for (int i = CNT; i > 0; i--) {
           // incomeUserList.add(new IncomeUser(i+3, "se" + i));
        }
        LOGGER.info("add arr finish : " + (System.currentTimeMillis()- time)+ " millisecond");
        long time2 = System.currentTimeMillis();
      //  List<IncomeUser> users = StreamEx.of(incomeUserList)
        List<IncomeUser> users = incomeUserList
                .stream()
                //.parallelStream()
                .filter(u -> {
                    for (MyUser user : myUserList) {
                        if (user.getId() == u.getId()) return true;
                    }
                    return false;
                })
               // .peek(user -> System.out.println(user.getId()))
                .collect(Collectors.toList());

        LOGGER.info("add arr finish : " + (System.currentTimeMillis()- time2)+ " millisecond");
        System.out.println(users.size());

      //  users.forEach(System.out::println);

        /*List<Pair< Integer, String>>  list1 = new ArrayList<>();
        list1.add(new Pair<>(1,"aaa"));
        list1.add(new Pair<>(2,"bbb"));

        List<Pair< Integer, Integer>>  list2 = new ArrayList<>();
        list2.add(new Pair<>(1,10));
        list2.add(new Pair<>(3,30));
        list2.add(new Pair<>(4,30));
        list2.add(new Pair<>(5,30));

        List<Triple<Integer, String, Integer>> arrayList = (nestedLoopsJoin(list1, list2));

        print(arrayList);

        List<Triple<Integer, String, Integer>> arrayList2 = (hashJoin(list1, list2));

        print(arrayList2);

        List<Triple<Integer, String, Integer>> arrayList3 = (nestedLoopsJoin(list1, list2, JoinType.LEFT));

        print(arrayList3);*/
    }

    private static <K, V1, V2> void print(List<Triple<K, V1, V2>> arrayList) {
        System.out.println("-------------------");
        for (Triple<K, V1, V2> item : arrayList) {
            System.out.println(item.getFirst() + " " + item.getSecond() + " " + item.getThird());
        }
    }

    public static <K, V1, V2> List<Triple<K, V1, V2>> nestedLoopsJoin(List<Pair<K, V1>> left, List<Pair<K, V2>> right) {
        List<Triple<K, V1, V2>> result = new ArrayList<>();
        for (Pair<K, V1> leftPair : left) {
            for (Pair<K, V2> rightPair : right) {
                if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                    result.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));
                }
            }
        }
        return result;
    }

    public static <K, V1, V2> List<Triple<K, V1, V2>> hashJoin(List<Pair<K, V1>> left, List<Pair<K, V2>> right) {
        Map<K, V2> hash = new HashMap<>(right.size());
        for (Pair<K, V2> rightPair : right) {
            hash.put(rightPair.getKey(), rightPair.getValue());
        }

        List<Triple<K, V1, V2>> result = new ArrayList<>();
        for (Pair<K, V1> leftPair : left) {
            if (hash.containsKey(leftPair.getKey())) {
                result.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), hash.get(leftPair.getKey())));
            }
        }

        return result;
    }

    public static <K, V1, V2> List<Triple<K, V1, V2>> nestedLoopsJoin(
            List<Pair<K, V1>> left,
            List<Pair<K, V2>> right,
            JoinType joinType
    ) {
        // Массив для обозначения ключей из правого списка, которым не нашлось пары в левом
        BitSet rightMatches = new BitSet(right.size());

        List<Triple<K, V1, V2>> result = new ArrayList<>();

        for (Pair<K, V1> leftPair : left) {
            // Флаг для обозначения ключей в левом списке, которым не нашлось пары в правом
            boolean match = false;
            for (ListIterator<Pair<K, V2>> iterator = right.listIterator(); iterator.hasNext(); ) {
                Pair<K, V2> rightPair = iterator.next();
                if (Objects.equals(leftPair.getKey(), rightPair.getKey())) {
                    result.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), rightPair.getValue()));

                    // Отмечаем пары
                    match = true;
                    rightMatches.set(iterator.previousIndex(), true);
                }
            }
            //  for (int i = 0; i < ; i++) {
            //  }
            // Заполняем несоответствия в левом списке
            if ((joinType == JoinType.LEFT || joinType == JoinType.FULL) && !match) {
                result.add(new Triple<>(leftPair.getKey(), leftPair.getValue(), null));
            }
        }

        // Заполняем несоответствия в правом списке
        if (joinType == JoinType.RIGHT || joinType == JoinType.FULL) {
            for (int i = 0; i < right.size(); ++i) {
                if (!rightMatches.get(i)) {
                    Pair<K, V2> rightPair = right.get(i);
                    result.add(new Triple<>(rightPair.getKey(), null, rightPair.getValue()));
                }
            }
        }
        return result;
    }
}