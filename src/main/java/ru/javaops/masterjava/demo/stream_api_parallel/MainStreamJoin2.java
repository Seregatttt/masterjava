package ru.javaops.masterjava.demo.stream_api_parallel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.masterjava.demo.example_model.IncomeUser;
import ru.javaops.masterjava.demo.example_model.MyUser;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainStreamJoin2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainStreamJoin2.class);
    private static final int CNT = 1000_000;
    private static final String TYPE_PRODUCT = "KKEEWWDD";
    private static final String TYPE_CHANEL = "SBLGTREOL";

    public static void main(String[] args) throws Exception {
        LOGGER.info("start");

        List<MyUser> myUserList = new ArrayList<>();
        List<IncomeUser> incomeUserList = new ArrayList<>();
        long time = System.currentTimeMillis();

        Map<String, MyUser> hash = new HashMap<>(myUserList.size());
        // 5000 000 = 1G  100e6 = 20G
        for (int i = 0; i <= CNT*55; i++) {

            if (i%500_000  == 0){
                System.out.println("sleep"+ i);
                Thread.sleep(30000);
          }
            hash.put(i + TYPE_PRODUCT + TYPE_CHANEL, new MyUser(i, TYPE_PRODUCT, TYPE_CHANEL, 100 * i));
        }

        for (int i = CNT; i > 0; i--) {
            incomeUserList.add(new IncomeUser(i + 3, TYPE_PRODUCT, TYPE_CHANEL, "se" + i, null));
        }

        LOGGER.info("add arr finish : " + (System.currentTimeMillis() - time) + " millisecond");

        long time2 = System.currentTimeMillis();

        List<IncomeUser> users = incomeUserList
                //.parallelStream()
                 .stream()
                //  .parallel()
//                .filter(u -> {
//                    if (hash.containsKey(u.getId() + u.getTypeProduct() + u.getTypeChanel())) return true;
//                    return false;
//                })
                .map(new Function<IncomeUser, IncomeUser>() {
                    @Override
                    public IncomeUser apply(IncomeUser el) {
                        if (hash.containsKey(el.getId() + el.getTypeProduct() + el.getTypeChanel())) {
                            el.setFlag("1");
                        }
                        return el;
                    }
                })
                .collect(Collectors.toList());

        LOGGER.info("filter : " + (System.currentTimeMillis() - time2) + " millisecond");
        System.out.println(users.size());

        /*for (int i = 0; i < 5; i++) {
            System.out.println(users.get(i));
        }
        System.out.println("--------------");
        for (int i = users.size()-1; i > CNT-5; i--) {
            System.out.println(users.get(i));
        }*/
    }
}