import { Component } from '@angular/core';
import { NotasComponent } from '../notas/notas.component';

@Component({
  selector: 'app-sections',
  standalone: true,
  imports: [NotasComponent],
  templateUrl: './sections.component.html',
  styleUrl: './sections.component.css'
})
export class SectionsComponent {

}
