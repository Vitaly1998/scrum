import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SvobodTaskWindowComponent } from './svobod-task-window.component';

describe('SvobodTaskWindowComponent', () => {
  let component: SvobodTaskWindowComponent;
  let fixture: ComponentFixture<SvobodTaskWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SvobodTaskWindowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SvobodTaskWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
