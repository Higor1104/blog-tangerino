import { CadastroUsuarioComponent } from './cadastro-usuario/cadastro-usuario.component';
import { ListarPostagemComponent } from './listar-postagem/listar-postagem.component';
import { DetalharPostagemComponent } from './detalhar-postagem/detalhar-postagem.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'blog', pathMatch: 'full' },
  { path: 'addUsuario', component: CadastroUsuarioComponent },
  { path: 'listarPostagens', component: ListarPostagemComponent },
  { path: 'detalharPostagem/:id', component: DetalharPostagemComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
