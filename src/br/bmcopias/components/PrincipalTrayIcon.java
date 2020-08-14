package br.bmcopias.components;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import br.bmcopias.configuration.ConfiguracaoPrincipal;
import br.bmcopias.layout.frame.ContainerGeral;
import br.bmcopias.service.FinanceiroService;
import br.bmcopias.util.Util;

public class PrincipalTrayIcon {
	private ContainerGeral pFrame;
	
	ConfiguracaoPrincipal props = ConfiguracaoPrincipal.getInstance();
	
	private String systema;
	private String versao;
	private String empresa;
	private FinanceiroService fs = new FinanceiroService();
	
	public PrincipalTrayIcon(ContainerGeral frame){
//		create();
		pFrame = frame;
	}
	
	public  void create(){
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        systema = props.getString("bmc.system.name");
        versao  = props.getString("bmc.system.version");
        empresa = props.getString("bmc.boleto.cedente");
        
        final PopupMenu popup   = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(Util.createImage("icon_security.gif", "tray icon"));
        final SystemTray tray   = SystemTray.getSystemTray();
        
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("Sobre");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Mostrar alertas Contas a Pagar");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Mostrar alertas Contas a Receber");
        
        cb1.setState(true);
        cb2.setState(true);
        
        Menu displayMenu = new Menu("Exibir");
        MenuItem preferencias = new MenuItem("Preferências");
        MenuItem sistema = new MenuItem("Sistema");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Sair");
        
        String nomeFont = "Arial";
        int tamanhoFont = 12;
        int i           = 0;
        
        aboutItem.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        cb1.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        cb2.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        displayMenu.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        preferencias.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        sistema.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        infoItem.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        noneItem.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        exitItem.setFont(Util.getFont(nomeFont, i , tamanhoFont));
        
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(preferencias);
        displayMenu.add(sistema);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popup.add(exitItem);
        
        trayIcon.setPopupMenu(popup);
        
        try {
        	trayIcon.setToolTip(systema + " " + versao);
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
        
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		if(pFrame.isVisible()){
        			pFrame.setVisible(false);
        			System.out.println("Visible = false");
        		}else{
        			pFrame.setVisible(true);
        			pFrame.toFront();
        			pFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Maximizing the frame
        			System.out.println("Visible = true");
        		}
				pFrame.pack();
            }
        });
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null,
                systema + " " + versao + " " + empresa+" 2010");
            }
        });
        
        cb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED){
                    trayIcon.setImageAutoSize(true);
                } else {
                    trayIcon.setImageAutoSize(false);
                }
            }
        });
        
        cb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb2Id = e.getStateChange();
                if (cb2Id == ItemEvent.SELECTED){
                    trayIcon.setToolTip("Sun TrayIcon");
                } else {
                    trayIcon.setToolTip(null);
                }
            }
        });
        
        
        Thread t = new Thread(
				  new Runnable()
					{
						public void run()
						{
							SwingUtilities.invokeLater(
								new Runnable()
								{
									public void run()
									{
										
//										for(int i = 0 ; i < 10000; i++){
//											while(true){
//											if(i == 100 || i == 5000 || i == 10000){
										
												boolean ctasPagar = fs.temContasAPagarVencidas();
												boolean ctasReceb = fs.temContasAReceberVencidas();
										
												if(ctasPagar || ctasReceb){
														trayIcon.displayMessage("Aviso",
																(ctasReceb ? "Existem Contas à Pagar pendentes \n " : "")
																+
												                (ctasPagar ? "Existem Contas à Receber pendentes \n " : "")
												                , 
												                TrayIcon.MessageType.WARNING);
												
												}
											
//											}
											
//											}
											
//										}
										
								        
									}
								}
							);
						}
					}
				);
				t.start();
        
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem)e.getSource();
                //TrayIcon.MessageType type = null;
                System.out.println(item.getLabel());
                if ("Error".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.ERROR;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "This is an error message", TrayIcon.MessageType.ERROR);
                    
                } else if ("Preferências".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.WARNING;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "Função não disponível", TrayIcon.MessageType.WARNING);
                    
                } else if ("Sistema".equals(item.getLabel())) {
                	pFrame.setVisible(true);
        			pFrame.toFront();
        			pFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Maximizing the frame
                } else if ("None".equals(item.getLabel())) {
                    //type = TrayIcon.MessageType.NONE;
                    trayIcon.displayMessage("Sun TrayIcon Demo",
                            "Função não disponível", TrayIcon.MessageType.NONE);
                }
            }
        };
        
        preferencias.addActionListener(listener);
        sistema.addActionListener(listener);
        infoItem.addActionListener(listener);
        noneItem.addActionListener(listener);
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });        
        
	}
	
	public void close(){}

}
