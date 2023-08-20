package com.example.demo.controller;

import com.example.demo.model.MauSac;
import com.example.demo.services.MauSacService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/mau-sac")
public class MauSacController {

    private final MauSacService mauSacService;

    public MauSacController(MauSacService mauSacService) {
        this.mauSacService = mauSacService;
    }

    @GetMapping
    public List<MauSac> getAll() {
        return mauSacService.getAll();
    }

    @PostMapping
    public void registerMauSac(@RequestBody MauSac mauSac) {
        mauSacService.addMauSau(mauSac);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMauSac(@PathVariable("id") UUID uuid) {
        mauSacService.deleteById(uuid);
    }

    @PutMapping(path = "{id}")
    public void update(
            @PathVariable("id") UUID uuid,
            @RequestParam(required = false) String ma,
            @RequestParam(required = false) String ten
    ) {
        mauSacService.updateMauSac(uuid, ma, ten);

    }

}
