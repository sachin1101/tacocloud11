package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Taco;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository

public class TacoRepositoryDBImpl implements TacoRepositoryDB {


    private JdbcTemplate jdbc;

    private Long tacoCounter=1L;

    @Autowired
    public TacoRepositoryDBImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    @Synchronized
    public Taco save(Taco tacoDesign) {

        if(tacoDesign==null)
        {
            return  tacoDesign;
        }

        
        if(tacoDesign.getId() ==null)
        {
            tacoDesign.setId(getNextTacoId());
        }

        tacoDesign.setCreatedAt(new Date());

        
        saveTacoDesignName(tacoDesign);
        saveTacoDesignIngredients(tacoDesign);
        
        
        return tacoDesign;

    }

    private void saveTacoDesignIngredients(Taco tacoDesign) {
        //Table :: Taco_Ingredients




        List<String> params = new ArrayList<>();


        tacoDesign.getIngredients().forEach((ingredient)-> {
                    params.add(tacoDesign.getId().toString());
                    params.add(ingredient);
                });

        https://docs.spring.io/spring/docs/3.0.0.M4/reference/html/ch12s04.html

        /*


        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
                        Types.BIGINT  ,  Types.VARCHAR
                ).newPreparedStatementCreator(
                        params);


         */

        String [][] tmpArr = {{"99", "ABC"},{"99", "DEF"}};


        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco_Ingredients (taco, ingredient) values (?, ?)",  Types.BIGINT  ,  Types.VARCHAR
                ).newPreparedStatementCreator(
                        tmpArr);




        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);






    }


    private void saveTacoDesignName(Taco tacoDesign) {

        //Table :: TACO


        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (id, name, createdAt) values (?, ?, ?)",
                        Types.BIGINT  ,  Types.VARCHAR, Types.TIMESTAMP
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                tacoDesign.getId(),
                                tacoDesign.getName(),
                                new Timestamp(tacoDesign.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

    }

    private Long getNextTacoId()
    {
        return tacoCounter++;
    }

    @PostConstruct
    private void  initTacoCounter()
    {
         tacoCounter = jdbc.queryForObject("select nvl(max(id),0) FROM taco", Long.class);

         log.info("Taco Counter in DB is ::" + tacoCounter);
        tacoCounter++;
    }

    
}
