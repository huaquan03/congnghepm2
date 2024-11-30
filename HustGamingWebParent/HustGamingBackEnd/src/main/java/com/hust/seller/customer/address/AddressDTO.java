package com.hust.seller.customer.address;

public class AddressDTO {
    private String city ;
    private String district ;
    private String ward ;
    private String addressDetail ;

    public AddressDTO(){}

    public AddressDTO(String city, String district, String ward, String addressDetail) {
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.addressDetail = addressDetail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
