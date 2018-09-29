package cn.itcast.storm;

import backtype.storm.Config;
import backtype.storm.topology.TopologyBuilder;

public class WordCountTopologMain {
    public static void main(String[] args) {


        //1.

        TopologyBuilder topologybuilder = new TopologyBuilder();

        topologybuilder.setSpout("mySpout", new MySpout(), 1);

        topologybuilder.setBolt("mybolt1", new MySplitBolt(), 2).shuffleGrouping("mySpout");

        topologybuilder.setBolt("mybolt2", new MySplitBolt(), 4).fieldsGrouping("mybolt1");


        //2.
        Config config = new Config();
        config.setNumWorkers(2);



    }
}
