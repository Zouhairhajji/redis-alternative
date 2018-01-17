/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.control.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author zouhairhajji
 */
@Controller
@RequestMapping(value = "welcome")
public class WelcomeController {
    
    @Autowired
    private BigTableService bigTableService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getWelcomePage(Model model){
        model.addAttribute("all_keys", this.bigTableService.getAllKeys());
        
        return "bigtable_views/list_bigtable";
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String getWelcomePage(
            @RequestParam(value = "idcommand[]", required = true) Long[] idCommands,
            @RequestParam(value = "ordre[]", required = true) Integer[] ordres,
            Model model){
          
        System.out.println("print idcommands");
        for(Long   idCommand : idCommands){
            System.out.println("idCommand : > " + idCommand);
        } 
        System.out.println("------------");
        System.out.println("print ordres");
        for(Integer   ordre : ordres){
            System.out.println("ordre : > " + ordre);
        } 
        return "mapage";
    }
    
}
