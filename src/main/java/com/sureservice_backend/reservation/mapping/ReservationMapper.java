package com.sureservice_backend.reservation.mapping;

import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import com.sureservice_backend.reservation.resource.CreateReservationResource;
import com.sureservice_backend.reservation.resource.ReservationResource;
import com.sureservice_backend.reservation.resource.UpdateReservationResource;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ReservationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public ReservationResource toResource(Reservation model){
        return mapper.map(model,ReservationResource.class);
    }

    public Page<ReservationResource> modelListPage(List<Reservation> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ReservationResource.class),pageable,modelList.size());
    }

    public List<ReservationResource> modelListToResource(List<Reservation> modelList){return mapper.mapList(modelList, ReservationResource.class); }

    public Reservation toModel(CreateReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }

    public Reservation toModel(UpdateReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }
}
