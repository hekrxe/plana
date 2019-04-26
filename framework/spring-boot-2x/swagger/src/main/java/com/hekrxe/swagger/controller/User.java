package com.hekrxe.swagger.controller;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanhuayou on 2019/04/12
 */
public class User extends Person {
    private Name name;
    private int[] array;
    @ApiModelProperty(value = "满意度分布",required = true)
    private Gender gender;
    private List<Phone> phoneList;
    private Phone[] phoneArray;
    private Address[] addressArray;
    private List<Address> addressList;

    public static class Address implements Serializable {
        private String[] addressArray;
        private List<String> addressList;
        private Name[] nameArray;
        private List<Name> nameList;

        public String[] getAddressArray() {
            return addressArray;
        }

        public void setAddressArray(String[] addressArray) {
            this.addressArray = addressArray;
        }

        public List<String> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<String> addressList) {
            this.addressList = addressList;
        }

        public Name[] getNameArray() {
            return nameArray;
        }

        public void setNameArray(Name[] nameArray) {
            this.nameArray = nameArray;
        }

        public List<Name> getNameList() {
            return nameList;
        }

        public void setNameList(List<Name> nameList) {
            this.nameList = nameList;
        }
    }

    public static class Phone implements Serializable {
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class Gender implements Serializable {
        private String gender;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class Name implements Serializable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Phone[] getPhoneArray() {
        return phoneArray;
    }

    public void setPhoneArray(Phone[] phoneArray) {
        this.phoneArray = phoneArray;
    }

    public Address[] getAddressArray() {
        return addressArray;
    }

    public void setAddressArray(Address[] addressArray) {
        this.addressArray = addressArray;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
