import java.util.*;
import java.awt.Color;
import java.io.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class Square extends PApplet implements java.lang.Cloneable{

	static public void main(String[] args) {
		PApplet.main(Square.class.getName());
	}
	//ArrayList a;
	int randomPoints = 4000;
	int d = 64;
	//double r=0.4*512;
	double r = 512 * sqrt((d+1)/ (PI * randomPoints));//square
	//double r = 512 * sqrt((d+1)/((float) randomPoints));//disc
	//double r = 256 * sqrt((d+1)/((float) randomPoints));//sphere

	float rotX, rotY = (float) 0.0;
	randomSpherePoints rsp;
	float min = 0;
	float max = 0;
	float avg = 0;
	int vertices = randomPoints;
	boolean e = false;
	int edges = 0;
	float zoom = 1;
	float angle = 0;
	float xdir = 0;
	float ydir = 0;
	int gap = 0;
	int clear=0;
	float max2=0;
	float min2=0;
	float avg2=0;
	boolean check2=true;
	float clique=0;
	float maxcolor=0;
	int[][] colors;
	boolean check3=false;
	boolean bpt=false;
	int bptcolor=0;
	int p;
	boolean x=false, slca=false;
	PrintWriter output,output2,output3,output4;
	String ps="", psc="";
	//PFont f;
	PFont Font1,Font2;
	
	int rectX;
	int rect1Y= -200, rect2Y = -100, rect3Y = 0, rect4Y = 100, rect5Y = 200;  
	int rectH = 50;     
	int rectW = 120;   

	boolean rect1Over = false;
	boolean rect2Over = false;
	boolean rect3Over = false;
	boolean rect4Over = false;

	//--------------------------------------------------------
	public void settings(){
		size(1000, 600, P3D);
	}
	public void setup() {
		rectX = 500-150;
		
		Font2 = createFont("AR DESTINE", 22);
		Font1 = createFont("AR BERKLEY", 18);
		textSize(16);
		output = createWriter("C:/Users/rd28d/Desktop//AlgoOut/benchmark10/colorDistribution.txt");
		output2 = createWriter("C:/Users/rd28d/Desktop/AlgoOut/benchmark10/originaldegre.txt");
		output3 = createWriter("C:/Users/rd28d/Desktop/AlgoOut/benchmark10/avgdegree.txt");
		output4 = createWriter("C:/Users/rd28d/Desktop//AlgoOut/benchmark10/priset.txt");

		
		smooth();
		stroke(0, 0,0);
		strokeWeight((float) 3.0);
		rsp = new randomSpherePoints(randomPoints, round((float) (width / 2.5)));
	}
	//--------------------------------------------------------
	public void draw() {
		
		background(0);
		
		translate(width * 0.5f,height * 0.5f);
		
		rsp.draw();
		scale(zoom);
		if (mousePressed) {

			rotY += (pmouseX - mouseX) * -0.002;
			rotX += (pmouseY - mouseY) * +0.002;
			println(round(frameRate) + " fps");
		}
		rotY += 0.002;
		update(mouseX, mouseY);
		stroke(204, 102, 0);
		fill(128);
		rect(rectX, rect1Y, rectW, rectH);
		fill(0);
		text("e", rectX+10, rect1Y+20);
		text("Display Edges", rectX+20, rect1Y+30);
		
		stroke(204, 102, 0);
		fill(120);
		rect(rectX, rect2Y, rectW, rectH);
		fill(0);
		text("m, d", rectX+10, rect2Y+20);
		text("Display min", rectX+20, rect2Y+30);
		text("and max", rectX+30, rect2Y+40);
		
		stroke(204, 102, 0);
		fill(128);
		rect(rectX, rect3Y, rectW, rectH);
		fill(0);
		text("s", rectX+10, rect3Y+20);
		text("Smallest last",rectX+18, rect3Y+28);
		text("& colouring",rectX+26, rect3Y+36);
		
		stroke(204, 102, 0);
		fill(128);
		rect(rectX, rect4Y, rectW, rectH);
		fill(0);
		text("b", rectX+10, rect4Y+20);
		text("Bipartite", rectX+20, rect4Y+30);
		
	}
	//--------------------------------------------------------
	public void keyPressed() {
		
		if (key == ' ') rsp = new randomSpherePoints(randomPoints, round((float) (width / 2.5)));

		if (key == 'i' || key == 'I') {
			zoom += .03;
		}
		if (key == 'R' || key == 'r') {
			//
			for(int i=0;i<rsp.maxPoints;i++){
				for (int j=0;j<rsp.maxPoints;j++){
					//if(rsp.newarray[0][i]==rsp.newpoints[0][j] && rsp.newarray[1][i]==rsp.newpoints[1][j]){
					output.print("\n"+rsp.newarray[2][i]+",");
					// output.print(rsp.newpoints[4][i]+",");

					break;
					// }
				}
			}  //


			for (int j=randomPoints-1;j>=0;j--){

				output2.print(rsp.plot1[1][j]+","+"\n");
				//(rsp.plot1[0][j]+ ","+rsp.plot1[1][j]+","+rsp.plot1[2][j]+"\n");
			}


			output.println();
			for(int i=1;i<=maxcolor;i++){
				output.println("color "+colors[1][i]+",");
			}

			output.flush(); // Writes the remaining data to the file
			output.close(); // Finishes the file
			output2.flush(); // Writes the remaining data to the file
			output2.close(); 
			output3.flush(); // Writes the remaining data to the file
			output3.close();
			output4.flush(); // Writes the remaining data to the file
			output4.close(); 
			print("writing data to files....");
		}

		if (key == 'o' || key == 'O') {
			zoom -= .03;
		}
		if (key == 'd' || key == 'D') {
			if (x == false) x = true;
			else x = false;
		}

		if (key == CODED) {
			if (keyCode == UP) {
				ydir = ydir + 10;
			} else if (keyCode == DOWN) {
				ydir = ydir - 10;
			} else if (keyCode == RIGHT) {
				xdir = xdir - 10;
			} else if (keyCode == LEFT) {

				xdir = xdir + 10;
			}
		}
		if (key == 's' || key == 'S') {
			angle = 0;
			zoom = 1;
			xdir = 0;
			ydir = 0;
		}
		if (key == 'e' || key == 'E') {
			if (e == false) e = true;
			else e = false;
		}

		if (key == 'm' || key == 'M') {

			print("Key pressed");
			print("\n m is pressed: "+r);
			//gap=1000;
			gap = (int)((randomPoints * 4 * r) / 512);
			print("GAP:" + gap);

			int count = 0;

			for (int i = 0; i < rsp.maxPoints; i++) {
				for (int j = 0; j < rsp.maxPoints; j++) {
					if (i != j) {

						if (dist(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]) <= r) {
							rsp.newpoints[2][i] = rsp.newpoints[2][i] + 1;
							rsp.newpoints[4][i] = rsp.newpoints[4][i] + 1;
							//   println(i + "  "+ j+"  "+rsp.newpoints[0][i]+ "  "+rsp.newpoints[1][i]+ "  "+rsp.newpoints[0][j]+ "  "+rsp.newpoints[1][j]);
							edges++;

						}
					}
				}
			}

			min = 1000000;
			for (int i = 0; i < rsp.maxPoints; i++) {
				avg = avg + rsp.newpoints[2][i];

				output3.print(avg/(i+1)+", \n");
				rsp.plot1[2][rsp.maxPoints-i-1]=avg/(i+1);

				if (rsp.newpoints[2][i] < min) {
					min = rsp.newpoints[2][i];
				}
				if (rsp.newpoints[2][i] > max) {
					max = rsp.newpoints[2][i];
				}

			}
			avg = avg / rsp.maxPoints;
			print("\n average degree :" + avg);
			print("\n Min degree:" + min);
			print("\n Max degree:" + max);
			print("\n radius:" + r / 256);

			print("m presses");

			max2=max;
			min2=min;
			avg2=avg;
		}

		if (key == 'b' || key == 'B') {


			p=p+1;
			bptcolor=bptcolor+1;
			print("\n p:"+p);
			print("\n bptcolor:"+bptcolor);
			int points=0,points2=0,points3=0;

			for (int i = 0; i < rsp.maxPoints; i++) {
				if(rsp.newarray[2][i]>p){
					rsp.newarray[2][i]=0;
					points++;
				}
			}

			for (int i = 0; i < rsp.maxPoints; i++) {
				int localcounter=0;
				for (int j = 0; j < rsp.maxPoints; j++) {
					if (i != j && rsp.newarray[2][i]==0 && rsp.newarray[2][j]==bptcolor) {

						if (dist(rsp.newarray[0][i], rsp.newarray[1][i], rsp.newarray[0][j], rsp.newarray[1][j]) <= r) {
							localcounter++;
							if(localcounter>=3){
								rsp.newarray[2][i]=p+1;
								points2++;
								break;
							}

						}
					}
				}
			}


			// now find independent set

			for (int i = 0; i < rsp.maxPoints; i++) {
				for (int j = 0; j < rsp.maxPoints; j++) {
					if (i != j && rsp.newarray[2][i]==p+1 && rsp.newarray[2][j]==p+1) {
						if (dist(rsp.newarray[0][i], rsp.newarray[1][i], rsp.newarray[0][j], rsp.newarray[1][j]) <= r) {
							rsp.newarray[2][j]=0;
						}
					}
				}
			}

			for (int i = 0; i < rsp.maxPoints; i++) {
				if(rsp.newarray[2][i]==p+1){
					points3++;
				}
			}
			int points4=0;
			for (int i = 0; i < rsp.maxPoints; i++) {
				if(rsp.newarray[2][i]==bptcolor){
					points4++;
				}
			}
			if(points==0){
				for (int i = 0; i < rsp.maxPoints; i++) {
					if(rsp.newarray[2][i]==0){
						points++;
					}
				}
				points=points+points3;
			}


			output4.println(points4+","+points+","+points2+","+points3);
			print("\n points remaining :"+points);
			print("\n points2 which are covered >=3 times: "+points2);
			print("\n points3 independent set points: "+points3);

			bpt=true;

		}


		if (key == 's' || key == 'S') {

			float removecounter=0;
			print("Calculating smallest last and coloring it");
			while (true) {
				if(min==max && check2==true){
					clique=min+1;
					println("clique size: "+min+1);
					check2=false;
				}

				//if(min!=max){   
				for (int i = 0; i < rsp.maxPoints; i++) {

					if (rsp.newpoints[3][i] == 1) {
						if (rsp.newpoints[2][i] == min) {
							rsp.plot1[0][(int)removecounter]=(int)rsp.newpoints[2][i];
							rsp.plot1[1][(int)removecounter]=(int)rsp.newpoints[4][i];

							//   println(rsp.newpoints[0][i]+ ","+rsp.newpoints[1][i]+","+rsp.newpoints[2][i]);
							rsp.newpoints[3][i] = 0;
							removecounter++;
							//rsp.newpoints[4][i]=removecounter;
							rsp.newarray[0][(int)(removecounter-1)]= rsp.newpoints[0][i];
							rsp.newarray[1][(int)(removecounter-1)]= rsp.newpoints[1][i];
							rsp.newarray[2][(int)(removecounter-1)]=0;
							if(clique>0){
								rsp.newpoints[5][i]=1;
							}
							break;
						}
					}
				}

				int stp = 0;
				int edp = (int) gap / 2;
				//int edp=rsp.maxPoints;
				for (int i = 0; i < rsp.maxPoints; i++) {
					//rsp.newpoints[2][i] = 0;
					clear=0;
					if (rsp.newpoints[3][i] == 1) {
						for (int j = stp; j < edp; j++) {
							if (i != j && rsp.newpoints[3][j] == 1) {
								if (dist(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]) <= r) {
									clear++;
								}
							}
						}
						rsp.newpoints[2][i] = clear;
					}

					if (i > gap / 2) {
						stp++;
					}
					if (edp != rsp.maxPoints) {
						edp++;
					}
				}


				if(min==0 && max==0){
					vertices=0; 
					break; 
				}
				min = 1000000;
				max = 0;
				vertices = 0;
				for (int i = 0; i < rsp.maxPoints; i++) {

					if (rsp.newpoints[3][i] == 1) {
						vertices++;

						if (rsp.newpoints[2][i] < min) {
							min = rsp.newpoints[2][i];
						}
						if (rsp.newpoints[2][i] > max) {
							max = rsp.newpoints[2][i];
						}
					}
				}

				if (vertices % 1000 == 0) 
				{
					print("\n" + vertices);
				}

			}
			avg = min;



			for(int i=rsp.maxPoints-1; i>=0; i--){
				colors= new int[2][(int)(max2)];
				for(int k=0;k<max2;k++){
					colors[0][k]=k;
					colors[1][k]=0;
					//    print(colors[k]);
				}

				for(int j=0; j<rsp.maxPoints; j++){
					if(i!=j){

						if (dist(rsp.newarray[0][i], rsp.newarray[1][i], rsp.newarray[0][j], rsp.newarray[1][j]) <= r){
							if(rsp.newarray[2][j]>0){
								colors[0][(int)rsp.newarray[2][j]]=0;
								//remove element from colors
							}
						}
					}
				}
				for(int k=0;k<max2;k++){
					if(colors[0][k]>0){
						rsp.newarray[2][i]=k;
						break;
					}
				}
			}
			maxcolor=0;
			for(int i=0;i<rsp.maxPoints;i++){
				if(rsp.newarray[2][i]>maxcolor){
					maxcolor=rsp.newarray[2][i];
				}
			}
			print("max num colors :" +maxcolor);

			for(int i=0;i<rsp.maxPoints;i++){
				int k=(int)(rsp.newarray[2][i]);
				colors[1][k]=colors[1][k]+1;
			}

			check3=true;
			p=(int)(maxcolor/3)-1; 
			ps="Primary set : color 1 to "+(p+1);

			slca=true;
		}


	}
	
	boolean overRect(int x, int y, int width, int height)  {
		if (mouseX >= x && mouseX <= x+width && 
				mouseY >= y && mouseY <= y+height) {
			return true;
		} else {
			return false;
		}
	}
	void update(int x, int y) {
		if ( overRect(rectX, rect2Y, rectW, rectH) ) {
			rect2Over = true;
			rect3Over = false;
			rect4Over = false;
			rect1Over = false;
		} else if ( overRect(rectX, rect1Y, rectW, rectH) ) {
			rect1Over = true;
			rect3Over = false;
			rect4Over = false;
			rect2Over = false;
		} else if ( overRect(rectX, rect3Y, rectW, rectH) ) {
			rect1Over = false;
			rect3Over = true;
			rect4Over = false;
			rect2Over = false;
		}else if ( overRect(rectX, rect4Y, rectW, rectH) ) {
			rect1Over = false;
			rect3Over = false;
			rect4Over = true;
			rect2Over = false;
		}else {
			rect2Over = rect3Over = rect4Over = rect1Over = false;
		}
	}
	public void mousePressed() {
		if (rect2Over) {
			println("button1 pressed");
			text("one",10,10);
		}
		if (rect1Over) {
			text("two",10,10);
		}
		if (rect3Over) {
			text("three",10,10);
		}
		if (rect4Over) {
			text("four",10,10);
		}
	}

	
	class randomSpherePoints {
		int maxPoints = 0;
		float newarray[][],plot1[][]; 
		float newpoints[][];
		int RGBcolors[][]; 
		//--------------------------------------------------------
		// create random sphere points
		//--------------------------------------------------------
		randomSpherePoints(int pointCount, float sphereRadius) {
			maxPoints = pointCount;

			newpoints=new float[6][pointCount]; 
			newarray= new float[3][pointCount];
			RGBcolors=new int[3][100];
			plot1=new float[3][pointCount];

			for(int i=1;i<100;i++){
				RGBcolors[0][i]=(int)random(0,255);
				RGBcolors[1][i]=(int)random(0,255);
				RGBcolors[2][i]=(int)random(0,255);
			}




			for (int ni = 0; ni < maxPoints; ni++) {
				newpoints[0][ni] = randomsquarePoint();
				newpoints[1][ni] = randomsquarePoint();

				float angle=random(0,359);
				float rad=random(0,65536);
				newpoints[2][ni] = (float) 0.0;
				newpoints[3][ni] = (float) 1.0; // availability
				newpoints[4][ni] = (float) 0.0; // incremental number
				newpoints[5][ni] = (float) 0.0; // color code
			}

			float tempx, tempy, temp1, temp2, temp3, temp4;
			for (int i = 0; i < maxPoints - 1; i++) {
				for (int j = 0; j < maxPoints - 1; j++) {
					if (newpoints[0][j] > newpoints[0][j + 1]) {
						tempx = newpoints[0][j];
						tempy = newpoints[1][j];
						temp1 = newpoints[2][j];
						temp2 = newpoints[3][j];
						temp3 = newpoints[4][j];
						temp4 = newpoints[5][j];



						newpoints[0][j] = newpoints[0][j + 1];
						newpoints[1][j] = newpoints[1][j + 1];
						newpoints[2][j] = newpoints[2][j + 1];
						newpoints[3][j] = newpoints[3][j + 1];
						newpoints[4][j] = newpoints[4][j + 1];
						newpoints[5][j] = newpoints[5][j + 1];



						newpoints[0][j + 1] = tempx;
						newpoints[1][j + 1] = tempy;
						newpoints[2][j + 1] = temp1;
						newpoints[3][j + 1] = temp2;
						newpoints[4][j + 1] = temp3;
						newpoints[5][j + 1] = temp4;



					}

				}

			}



		}

		//--------------------------------------------------------
		// draw random sphere points  
		//--------------------------------------------------------
		void draw() {

			scale(zoom);
			rotate(angle);
			translate(xdir, ydir);
			strokeWeight((float) 2.0);
			fill(0);

			for (int ni = 0; ni < maxPoints; ni++) {

				strokeWeight((float) 4.0);
				if(newarray[2][ni]==bptcolor){
					stroke(40,200,40);//bipartite lines
					point(newarray[0][ni], newarray[1][ni]);
				}
				else if(newarray[2][ni]==p+1){
					stroke(250, 230, 20);
					point(newarray[0][ni], newarray[1][ni]);
				}else {
					stroke(0,0,0);
				}


				stroke(40,40,200); 

				strokeWeight((float) 3.0);
			}
			stroke(245,40,140);

			if(slca==false){
				for (int ni1 = 0; ni1 < maxPoints; ni1++) {
					if (newpoints[3][ni1] == 1 || newpoints[5][ni1]==1) {
						point(newpoints[0][ni1], newpoints[1][ni1]);
					}
				}
			}

			if(bpt==false){
				strokeWeight((float) 4.0);
				for (int ni = 0; ni < maxPoints; ni++) {
					int c=(int)newarray[2][ni];
					stroke(RGBcolors[0][c],RGBcolors[1][c],RGBcolors[2][c]); 
					point(newarray[0][ni], newarray[1][ni]);
					stroke(0,0,0); 
				}
			}

			if (e == true) {
				strokeWeight((float) 0.8);
				stroke(0,250,195);
				
				for (int i = 0; i < rsp.maxPoints; i++) {
					for (int j = 0; j < rsp.maxPoints; j++) {
						if (i != j && rsp.newpoints[3][j] == 1 && rsp.newpoints[3][i] == 1 || i!=j && rsp.newpoints[5][i]==1 && rsp.newpoints[5][j]==1) {

							if (dist(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]) <= r) {

								line(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]);
								

							}
						}
					}


				}
				stroke(0,0,0);
				strokeWeight((float) 3.0);
			}
			if(x ==true){
				strokeWeight((float) 0.8);

				for (int i = 0; i < rsp.maxPoints; i++) {
					for (int j = 0; j < rsp.maxPoints; j++) {
						if (i != j && rsp.newpoints[3][j] == 1 && rsp.newpoints[3][i] == 1 || i!=j && rsp.newpoints[5][i]==1 && rsp.newpoints[5][j]==1) {

							if(rsp.newpoints[2][i]==max){
								if (dist(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]) <= r) {
									stroke(230,10,10);
									line(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]);
									stroke(0,0,0);
									//     print(" \n"+rsp.points[i].x, rsp.points[i].y);

								}
							}else if(rsp.newpoints[2][i]==min){
								
								if (dist(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]) <= r) {
									stroke(255,255,0);
									line(rsp.newpoints[0][i], rsp.newpoints[1][i], rsp.newpoints[0][j], rsp.newpoints[1][j]);
									stroke(0,0,0);
									
								}

							}
						}
					}


				}

				strokeWeight((float) 3.0);
			}

			if (bpt == true) {
				strokeWeight((float) 0.7);
				stroke(162,15,165);
				for (int i = 0; i < rsp.maxPoints; i++) {
					for (int j = 0; j < rsp.maxPoints; j++) {
						if (i != j) {
							if(rsp.newarray[2][i] == bptcolor  &&  rsp.newarray[2][j] == p+1 || rsp.newarray[2][j] == bptcolor  &&  rsp.newarray[2][i] == p+1){          
								if (dist(rsp.newarray[0][i], rsp.newarray[1][i], rsp.newarray[0][j], rsp.newarray[1][j]) <= r) {
									line(rsp.newarray[0][i], rsp.newarray[1][i], rsp.newarray[0][j], rsp.newarray[1][j]);
									
								}
							}
						}
					}
				}
				strokeWeight((float) 3.0);
			}


			fill(20,20,200); //color of wireless sensor network
			//textSize(22);
			textFont(Font2);
			text("ALGORITHM ENGINEERING PROJECT: WIRELESS SENSOR NETWORK",-350 ,-280);
			fill(204, 102, 0); //text color for info
			//textSize(12);  
			textFont(Font1);

			text("Number of Vertices: ", -495, -210);
			text((int)randomPoints, -330, -210);

			text("Minimum degree: ", -495, -180);
			
			text((int)min2, -330, -180);

			text("Maximum degree: ", -495, -150);
			text((int)max2, -330, -150);

			text("Average degree: ", -495, -120);
			text((int)d, -330, -120);//avg2

			text("Threshold: ", -495, -90);
			text((float)(r / 256), -330, -90);

			if(bpt==true){
				//text(ps, -495, 70);
				psc="Bipartite Graph: "+bptcolor;
				text(psc, -75, 280);
				check3 = false;
			}

			if(check3==true){
				textSize(12);
				textFont(Font1);
				//fill(255,255,255);
				for(int i=1;i<=maxcolor;i++){
					int c=(int)newarray[2][i];
					fill(RGBcolors[0][c],RGBcolors[1][c],RGBcolors[2][c]);
					if(i<=30){
					text("colour"+i , -495, -70+i*12);//-275
					}
					else
						text("colour"+i, -430, -70+(i%30)*12);//-275
				}
				textSize(16);
			}
			//fill(255);


		}
		
		float randomsquarePoint() {
			float a = 0, b = 0, c = 0, d = 0, k = 99;
			a = random(-256, 256);
			return a;
		}
		float randomDiskPoint(float angle,float rad) {
			float a = 0, b = 0, c = 0, d = 0, k = 99;
			//float angle=random(1)*3.1415*2;
			a =sqrt(rad)*cos(angle);
			System.out.println(a);
			return a;
		}
		float randomDiskPointY(float angle, float rad) {
			float a = 0, b = 0, c = 0, d = 0, k = 99;
			//float angle=random(1)*3.1415*2;
			a =sqrt(rad)*sin(angle);
			return a;
		}
	}
}
