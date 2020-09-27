import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Usuario } from './usuario';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject;
    public currentUser: Observable;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): Usuario {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post('http://localhost:8080/api/blog/usuarios/login', { username, password })
            .pipe(map(token => {
                var usuario:Usuario = new Usuario();
                usuario.login = username;
                //usuario.token = token;
                localStorage.setItem('currentUser', JSON.stringify(usuario));
                this.currentUserSubject.next(usuario);
                return usuario;
            }));
    }

    logout() {
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}