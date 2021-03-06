package TrainGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Search extends JFrame {
	private TrainTest main;
    private Booking booking;
    private Pay pay;
    
    private String start="광주"; //출발지
    private String arrival="광주"; //도착지
    private int month=1; //달
    private int day=1; //일
    private String train=""; //db 구분항목
    private String warningMessage; //경고메세지
    private boolean check=false;
    private int today_month; //오늘 달
	private int today_day; //오늘 날짜
    
    Date date = new Date();
	SimpleDateFormat sdf1 = new SimpleDateFormat("MM"); //월
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd"); //일
    
    Font f1=new Font("나눔바른고딕",Font.PLAIN,18);//기본폰트
    
    Passenger p1;
    
    public Search() {
    	
    }
    
	public Search(Passenger p1) {
		this. p1 = p1;
	}
		public Passenger getPassenger() {
			return p1;
		}
		public void setPassenger() {
			this.p1 = p1;
		}
		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getArrival() {
			return arrival;
		}

		public void setArrival(String arrival) {
			this.arrival = arrival;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}
		
		public String getTrain() {
			return train;
		}
		

		public String getWarningMessage() {
			return warningMessage;
		}

		public void setWarningMessage(String warningMessage) {
			this.warningMessage = warningMessage;
		}

		public void SearchFrame() {
			Frame searchframe = new Frame();
			setTitle("기차표예매"); // 창 제목 설정
			setSize(500,500); // 창 크기 설정
			setResizable(false); // T- 창 크기 조절 o F- 창 크기 조절 x
			setLocation(600,150); // 창 뜨는 위치 설정
			setDefaultCloseOperation(EXIT_ON_CLOSE); // 창 꺼지는거
			
			JPanel panel = new JPanel();
			placeButton(panel);
			add(panel);
			
			setVisible(true);
		}
		public void placeButton(JPanel panel) {
			today_month=Integer.parseInt(sdf1.format(date));
			today_day=Integer.parseInt(sdf2.format(date));
			int next_month;
			if(today_month==12) {
				next_month=1;
			}else {
				next_month=Integer.parseInt(sdf1.format(date))+1;
			}
			
			
			
			//광주(1),대구(2),대전(3),부산(4),서울(5),울산(6),인천(7)
			//날짜
			//8:00(1) 10:00(2) 12:00(3) 14:00(4) 16:00(5) 18:00(6) 20:00(7) 22:00(8) 
			//ppmdt
			String[] station = new String[] {"광주","대구","대전","부산","서울","울산","인천"};
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);//바탕색
				
			String[] monthChoice = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
			panel.setLayout(null);
			
			String[] dayChoice1 = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
					,"21","22","23","24","25","26","27","28","29","30","31"};
			
			panel.setLayout(null);
			
			JLabel startL = new JLabel("출발지");
			startL.setBounds(30, 20, 50, 80);
			panel.add(startL);
			startL.setFont(f1);
			
			
			JComboBox startBox = new JComboBox(station);
			startBox.setBounds(100, 40, 80, 30);
			panel.add(startBox);
			startBox.setBackground(Color.white);
			startBox.setFont(f1);
			startBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setStart((String)startBox.getSelectedItem());
			    	p1.setStart(start);
				}
			});
			

			
			JLabel arrive = new JLabel("도착지");
			arrive.setBounds(210, 20, 50, 80);
			panel.add(arrive);
			arrive.setFont(f1);
			
			JComboBox arriveBox = new JComboBox(station);
			arriveBox.setBounds(280, 40, 80, 30);
			panel.add(arriveBox);
			arriveBox.setBackground(Color.white);
			arriveBox.setFont(f1);
			arriveBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setArrival((String)arriveBox.getSelectedItem());
			    	p1.setArrival(arrival);
			    	
				}
			});
			
			JLabel date = new JLabel("날짜");
			date.setBounds(30, 80, 50, 80);
			panel.add(date);
			date.setFont(f1);
			
			
		    JComboBox dayBox = new JComboBox(dayChoice1);
			
			JComboBox monthBox = new JComboBox(monthChoice);
			monthBox.setBounds(100, 100, 80, 30);
			panel.add(monthBox);
			monthBox.setBackground(Color.white);
			monthBox.setFont(f1);
			monthBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setMonth(monthBox.getSelectedIndex()+1);
			    	p1.setMonth(getMonth());
			    	
			    	if(month!=today_month && month!=next_month) {
				    		if(check==true) {
				    			
				    		}else {
				    			warningMessage= today_month+"월 "+today_day+"일 부터"+next_month+"월"+today_day+"일 까지만 예매가 가능합니다.";
								warningFrame();
				    		}
				    }
				    	
				}
			});
			
			dayBox.setBounds(250, 100, 80, 30);
			panel.add(dayBox);
			dayBox.setBackground(Color.white);
			dayBox.setFont(f1);
			dayBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setDay(dayBox.getSelectedIndex()+1);
			    	p1.setDay(getDay());
			    	if((month==2 && day>28)||((month==4||month==6||month==9||month==11)&&day>30)) {
			    		warningMessage="                            올바르지 않은 날짜입니다";
						warningFrame();
			    	}else if((day>=today_day&&month==today_month)||(day<=today_day)&&month==next_month) {
			    		
			    	}else{
			    		if(check==true) {
			    			
			    		}else {
			    			warningMessage= today_month+"월 "+today_day+"일 부터"+next_month+"월 "+today_day+"일 까지만 예매가 가능합니다.";
							warningFrame();
			    		}
					}
				}
			});
			
			
			JLabel monthL = new JLabel("월");
			monthL.setBounds(200, 80, 50, 80);
			panel.add(monthL);
			monthL.setFont(f1);
		
			JLabel dayL = new JLabel("일");
			dayL.setBounds(350, 80, 50, 80);
			panel.add(dayL);
			dayL.setFont(f1);
			
			JButton next = new JButton("다음▶");
			next.setBounds(350, 400, 100, 40);
			panel.add(next);
		    next.setBackground(Color.white);
		    next.setFont(f1);
		    
		    next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((day>=today_day&&month==today_month)||(day<=today_day&&month==next_month))&&(start.equals(arrival)==false)) {
					makeDB();
					main.showBooking();
				}else {
					if(start.equals(arrival)==true) {warningMessage="                       출발지와 도착지가 동일합니다";}
					else {
						warningMessage= today_month+"월 "+today_day+"일 부터"+next_month+"월"+today_day+"일 까지만 예매가 가능합니다.";
						warningFrame();
					}
				}
				
			}
			});
		    
		}
		public void makeDB(){
			train="";
	    	train+=changeStationToNum(start)+changeStationToNum(arrival);
	    	
			if(month<10) {train+="0"+month;}
			else {train+=month;}
			
			if(day<10) {train+="0"+day;}
			else {train+=day;}
	    }  
		public String changeStationToNum(String station) {
			switch(station)
			{
			case "광주" : station = "1"; break;
			case "대구" : station = "2"; break;
			case "대전" : station = "3"; break;
			case "부산" : station = "4"; break;
			case "서울" : station = "5"; break;
			case "울산" : station = "6"; break;
			case "인천" : station = "7"; break;
			default: break;
			}
			return station;
		}
		public void setMain(TrainTest main) {
	        this.main = main;
	    }
		public void warningFrame() {
			
			JFrame warning = new JFrame();
			warning.setTitle("경고"); // 창 제목 설정
			warning.setSize(400,200); // 창 크기 설정
			warning.setResizable(false); // T- 창 크기 조절 o F- 창 크기 조절 x
			warning.setLocation(800,350); // 창 뜨는 위치 설정
			
			
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			JLabel warningMessageL = new JLabel();
			warningMessageL.setText(warningMessage);
			warningMessageL.setBounds(40, 40,300, 60);
	        panel.add(warningMessageL);
	        warningMessageL.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
	        
	        JButton yes = new JButton("확인");
	        yes.setBounds(160, 100, 80, 30);
	        panel.add(yes);
	        yes.setBackground(Color.white);
	        yes.setFont(f1);
	        
	        yes.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	warning.setVisible(false);
			    	check=false;
				}
			});
	        warning.add(panel);
	        warning.setVisible(true);
	        check = true;
			
		}

}