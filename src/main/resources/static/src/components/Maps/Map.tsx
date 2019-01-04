import * as React from "react";
import * as d3 from "d3";
import { TransitionGroup, CSSTransition } from "react-transition-group";
import { IMapProps, IMapState, ICircleEvent } from "../../types/MapTypes";
import { State } from "./State";
import * as stateIds from "../../data/us-states-ids.json";
import * as stateFeatures from "../../data/california-counties.json";
import { GeoProjection } from "d3";
import "./Map.scss";

// https://bl.ocks.org/mbostock/4e3925cdc804db257a86fdef3a032a45

export class Map extends React.Component<IMapProps, IMapState> {

    private width = window.innerWidth;
    private height = 600;
    private center: [number, number] = [-120, 37];
    private offsetLeft = 900;
    private offsetTop = 550;
    private scaleRate = 30;

    constructor(props: any) {
        super(props);
        
        this.generateMap = this.generateMap.bind(this);
        this.generatePath = this.generatePath.bind(this);
        this.generateViewBox = this.generateViewBox.bind(this);
        this.onCircleInteraction = this.onCircleInteraction.bind(this);
        this.updateDimensions = this.updateDimensions.bind(this);
        this.handleMouseMove = this.handleMouseMove.bind(this);
        this.handleMouseUp = this.handleMouseUp.bind(this);
        this.handleMouseDown = this.handleMouseDown.bind(this);
        this.handleTouchStart = this.handleTouchStart.bind(this);
        this.handleTouchMove = this.handleTouchMove.bind(this);
        this.zoomIn = this.zoomIn.bind(this);
        this.zoomOut = this.zoomOut.bind(this);

        this.state = {
            states: stateIds,
            maps: null,
            stations: [],
            tooltipActive: false,
            translateX: 0,
            translateY: 0,
            translateOn: false,
            zoom: this.scaleRate,
            startPageX: 0,
            startPageY: 0
        };
    }

    private translatePosition(pageX: number, pageY: number): void {
        if (this.state.translateOn) {
            let pageXDiff = pageX - this.state.startPageX;
            let pageYDiff = pageY - this.state.startPageY;
            this.setState({translateX: pageXDiff, translateY: pageYDiff});
        }
    }

    private handleMouseMove({ pageX, pageY, clientX, clientY }: any): void {
        this.translatePosition(pageX, pageY);
    }

    private handleMouseUp({ pageX, pageY, clientX, clientY }: any): void {
        this.setState({startPageX: pageX, startPageY: pageY});
    }

    private handleMouseDown({ pageX, pageY, clientX, clientY }: any): void {
        this.setState({startPageX: pageX, startPageY: pageY});
        this.setState({translateOn: !this.state.translateOn});
    }

    private handleTouchStart({ pageX, pageY, clientX, clientY }: any): void {
        this.setState({startPageX: pageX, startPageY: pageY});
        this.setState({translateOn: !this.state.translateOn});
    }

    private handleTouchMove({ pageX, pageY, clientX, clientY }: any): void {
        this.translatePosition(pageX, pageY);
    }

    private updateDimensions(): void {
        this.width = window.innerWidth;
    }

    private zoomIn(): void {
        this.setState({zoom: this.state.zoom + 200});
    }

    private zoomOut(): void {
        this.setState({zoom: this.state.zoom - 200});
    }

    public componentDidMount(): void {
        this.setState({
            maps: null, translateX: 0, translateY: 0, 
            translateOn: false, zoom: this.scaleRate
        });
    }

    public componentDidUpdate(): void {

    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({maps: nextProps.maps, stations: nextProps.stations});
    }

    private projection(): GeoProjection {
        return d3.geoMercator()
                .center(this.center)
                .translate([ (this.width / 2 + this.offsetLeft + this.state.translateX), (this.height / 2) + this.offsetTop + this.state.translateY])
                .scale(this.width * this.scaleRate + this.state.zoom);
    }

    private onCircleInteraction(event: any): void {
        let output: ICircleEvent = {
            x: event.pageX,
            y: event.pageY,
            target: event.target
        }
        this.props.hoverCallback(output);
    }

    private generateCircles(): JSX.Element {
        if (this.state.stations.length > 0) {
            return (
                <TransitionGroup component={null}>
                    {this.state.stations.map((feature: any, i: number) => {
                        const fill = "steelblue";
                        const projection = this.projection();
                        const coords: [number, number] = [feature.gtfsLongitude, feature.gtfsLatitude];
                        const locations = projection(coords) || [0, 0];  
                        return (
                            <CSSTransition
                                key={feature.abbr}
                                classNames={`state-transition-${feature.abbr}`}
                                appear={true}
                                timeout={5000}>
    
                                <g className="circle-container">
                                    <circle
                                        className={`states-circle raw state-transition-circle-${feature.abbr}`}
                                        r={2}
                                        data-index={i}
                                        cx={locations[0]}
                                        cy={locations[1]}
                                        fill={fill}
                                        stroke="#000000"
                                        strokeWidth={0.5}
                                        onMouseEnter={this.onCircleInteraction}
                                        opacity={0.75}
                                    />
                                </g>
    
                            </CSSTransition>
                        );
                    })}
                </TransitionGroup>
            );
        } else {
            return (<div>test</div>);
        }
    }

    private generatePath(geoPath: any, data: any): JSX.Element | undefined {
        const mapType = "US";

        return (
            <TransitionGroup component={null}>
                {data.map((feature: any, i: number) => {
                    const fill = "#0de298";
                    const path = geoPath(feature);
                    return (
                        <CSSTransition
                            key={i}
                            classNames={`state-transition-${i}`}
                            appear={true}
                            timeout={5000}
                        >

                            <State
                                mapType={mapType}
                                stateName={feature.properties.stateName}
                                stations={this.state.stations}
                                path={path}
                                feature={feature}
                                i={i}
                                fill={fill}
                                radius={40}
                            />

                        </CSSTransition>
                    );
                })}
            </TransitionGroup>
        );
    }

    private generateViewBox(): string {
        return "0 300 480 300";
    }

    public generateMap(path: d3.GeoPath): JSX.Element | undefined {
        if (stateFeatures["features"]) {
            return this.generatePath(path, stateFeatures["features"]);
        } else {
            return (<div>test</div>);
        }
    }

    public render(): JSX.Element {
        const path = d3.geoPath().projection(this.projection());

        return (
            <div className="map-container">
                <svg
                    className={`map US`}
                    id="bart-map"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox={this.generateViewBox()}
                    onMouseMove={ this.handleMouseMove }
                    onMouseUp={ this.handleMouseUp }
                    onMouseDown={ this.handleMouseDown }
                    onTouchStart={ this.handleTouchStart }
                    onTouchMove={ this.handleTouchMove }
                    onTouchEnd={ this.handleMouseUp }
                    width={this.width}
                    height={this.height}
                >
                    {this.generateMap(path)}
                    {this.generateCircles()}
                </svg>

                <div className="card">
                    <div className="row align-items-center">
                        <div 
                            className="btn btn-primary col"
                            onClick={this.zoomIn}
                        >Zoom In</div>
                        <div 
                            className="btn btn-warning col"
                            onClick={this.zoomOut}
                        >Zoom Out</div>
                    </div>
                </div>
            </div>
        );
    }

}