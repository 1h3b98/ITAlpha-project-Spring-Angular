import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartpointsComponent } from './chartpoints.component';

describe('ChartpointsComponent', () => {
  let component: ChartpointsComponent;
  let fixture: ComponentFixture<ChartpointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartpointsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartpointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
