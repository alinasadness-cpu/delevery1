package ru.netology.delivery.data;

import lombok.Value;

@Value
public class UserInfo {
    String city;
    String date;
    String name;
    String phone;
}