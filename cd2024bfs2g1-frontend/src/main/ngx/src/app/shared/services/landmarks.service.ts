import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CONFIG } from '../../app.config';

@Injectable({
  providedIn: 'root'
})
export class LandmarksService {

  constructor(private http: HttpClient) { }  

  getLandmark(route_id: number): Observable<any> {
    const url = CONFIG.apiEndpoint + '/routes/landmark/search'
    const headers = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Authorization', `Bearer ${JSON.parse(localStorage.getItem('com.campusdual.cd2024bfs2g1')).session.id}`)
    const body = {
      filter: { route_id },
      columns: [
        "landmark_id",
        "name",
        "description",
        "opening_time",
        "closing_time",
        "coordinates"
      ]
    }
    return this.http.post(url, body, { headers })

  }
}
