import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TropheyComponent } from './trophey.component';

describe('TropheyComponent', () => {
  let component: TropheyComponent;
  let fixture: ComponentFixture<TropheyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TropheyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TropheyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
