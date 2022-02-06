import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';
interface MyWindow extends Window {
  rudderanalytics: any;
 }
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Shreya hello-angular';
  trackClickEvent() {
    console.log("Cicked");
    window.rudderanalytics.track('Learn Angular Link Clicked');
  }
}