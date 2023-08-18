package com.example.springdemo.mybatis.entity;

public class Food {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column food.Id
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column food.name
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column food.price
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    private String price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column food.msg
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    private String msg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column food.date
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    private String date;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table food
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public Food(Integer id, String name, String price, String msg, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.msg = msg;
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table food
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public Food() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column food.Id
     *
     * @return the value of food.Id
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column food.Id
     *
     * @param id the value for food.Id
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column food.name
     *
     * @return the value of food.name
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column food.name
     *
     * @param name the value for food.name
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column food.price
     *
     * @return the value of food.price
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column food.price
     *
     * @param price the value for food.price
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column food.msg
     *
     * @return the value of food.msg
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column food.msg
     *
     * @param msg the value for food.msg
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column food.date
     *
     * @return the value of food.date
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column food.date
     *
     * @param date the value for food.date
     *
     * @mbg.generated Fri Aug 18 13:08:55 CST 2023
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}