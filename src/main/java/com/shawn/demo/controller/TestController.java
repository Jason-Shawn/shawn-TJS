package com.shawn.demo.controller;

import com.shawn.demo.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TestController {
    private static volatile Map<Object, Object> mapPool = new HashMap<>();



    @RequestMapping(value = "/linked")
    public Result linked(List<Node> nodes){
        int n = nodes.size();
        Set<Node> set = new HashSet<>();
        for (Node node : nodes) {
            int parentPos = node.getParentPos();
            if (parentPos >= 0) {
                if (set.contains(node)) {
                    return new Result(true, node);
                } else {
                    set.add(nodes.get(parentPos));
                }
            }
        }

        return new Result(false, null);
    }
    @PostMapping(value = "/students")
    public String[] students(@RequestBody int[] params){
        String[] i = new String[params.length];
        System.out.println(params);
        for (int j=0;j<params.length ; j++) {
            i[j] = params[j] >=60 ? (params[j] >= 90 ? "A" : "B") : "C";
        }

        return i;
    }
    @PostMapping("/pool")
    public String pool(@RequestBody Map<Object, Object> params){
        if (params == null) {
            return null;
        }
        for (Map.Entry<Object, Object> m : params.entrySet()) {
            mapPool.put(m.getKey(), m.getValue());
        }
        return "新增成功";
    }

    @RequestMapping(value = "/pool/{key}",method = RequestMethod.GET)
    public Object poolGet(@RequestParam String key){
        if (mapPool.containsKey(key)) {
            return mapPool.get(key);
        }
        return "404";
    }

    @RequestMapping(value = "/pool/{key}",method = RequestMethod.DELETE)
    public Object poolDel(@RequestParam String key){
        if (mapPool.containsKey(key)) {
            mapPool.remove(key);
            return "202";
        }
        return "404";
    }

    @RequestMapping(value = "/brackets",method = RequestMethod.POST)
    public String brackets(@RequestBody StringText stringText){
        String s = stringText.getText();
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    @PostMapping("/trains")
    public Integer trains(@RequestBody Schedule schedule){

        String[] departures1 = schedule.getDeparture();
        String[] arrivals1 = schedule.getArrival();
        int n = schedule.getArrival().length;
        List<Train> trainList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] arrivals = arrivals1[i].split(":");
            String[] departures = departures1[i].split(":");
            trainList.add(new Train(Integer.parseInt(arrivals[0])* 60 + Integer.parseInt(arrivals[1])
                    , Integer.parseInt(departures[0]) * 60 + Integer.parseInt(departures[1])));
        }

        trainList.sort(Comparator.comparingInt(Train::getArrival));

        int pnt = 0;
        int max = 1;
        while (pnt < trainList.size()) {
            Integer right = trainList.get(pnt).getDeparture();
            int cnt = 1;
            int pnt2 = pnt + 1;
            while (pnt2 < trainList.size()) {
                Integer arrival1 = trainList.get(pnt2).getArrival();
                if (arrival1 <= right) {
                    right = Math.min(trainList.get(pnt2).getDeparture(), right);
                    cnt++;
                } else {
                    break;
                }
                pnt2++;
            }
            max = Math.max(max, cnt);
            pnt = pnt2;
        }

        return max;
    }

}
