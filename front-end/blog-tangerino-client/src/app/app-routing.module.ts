import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';
import { ListarPostagemComponent } from './listar-postagem/listar-postagem.component';
import { DetalharPostagemComponent } from './detalhar-postagem/detalhar-postagem.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './utils/auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent},
  { path: 'cadastrarUsuario', component: CadastroUsuarioComponent },
  { path: 'listarPostagens', component: ListarPostagemComponent, canActivate: [AuthGuard] },
  { path: 'detalharPostagem/:id', component: DetalharPostagemComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const appRoutingModule = RouterModule.forRoot(routes);