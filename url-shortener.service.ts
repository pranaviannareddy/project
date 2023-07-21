import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UrlShortenerService {

  constructor(private http: HttpClient) { }

  shortenURL(originalURL: string) {
    return this.http.post<string>('/api/shorten', originalURL);
  }
}
