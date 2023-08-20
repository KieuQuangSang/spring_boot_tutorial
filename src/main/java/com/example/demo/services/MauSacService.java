package com.example.demo.services;

import com.example.demo.model.MauSac;
import com.example.demo.repositories.MauSacReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacService {

    private final MauSacReprository mauSacReprository;

    @Autowired
    public MauSacService(MauSacReprository mauSacReprository) {
        this.mauSacReprository = mauSacReprository;
    }

    public List<MauSac> getAll() {
        return mauSacReprository.findAll();
    }

    public void addMauSau(MauSac mauSac) {

        Optional<MauSac> optional = mauSacReprository.findAllByMa(mauSac.getMa());
        if (optional.isPresent()){
            throw new RuntimeException("Mã màu đã tồn tại");
        }
            mauSacReprository.save(mauSac);


    }

    public void deleteById(UUID uuid) {
        boolean check = mauSacReprository.existsById(uuid);
        if (!check){
            throw new RuntimeException("Màu không tồn tại");
        }

        mauSacReprository.deleteById(uuid);
    }

    @Transactional
    public void updateMauSac(UUID uuid, String ma, String ten) {
        MauSac mauSac = mauSacReprository.findById(uuid).orElseThrow(() -> new RuntimeException("Màu không tồn tại"));
        if (ma != null &&
        ma.length() > 0 &&
        !Objects.equals(mauSac.getMa(), ma)){
            Optional<MauSac> optional = mauSacReprository.findAllByMa(ma);
            if(optional.isPresent()){
                throw new RuntimeException("Mã màu đã tồn tại");
            }
            mauSac.setMa(ma);
        }

        if (ten != null &&
                ten.length() > 0 &&
                !Objects.equals(mauSac.getTen(), ten)){
            mauSac.setTen(ten);
        }
    }
}
